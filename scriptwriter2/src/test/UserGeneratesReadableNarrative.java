package test;

import com.youdevise.test.narrative.Action;
import com.youdevise.test.narrative.Actor;
import com.youdevise.test.narrative.Extractor;
import com.youdevise.test.narrative.Given;
import com.youdevise.test.narrative.Then;
import com.youdevise.test.narrative.When;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;


public class UserGeneratesReadableNarrative {

    private File codeDir;

    @Before public void createCodeDirectory() throws IOException {
        codeDir = File.createTempFile("narrative-code", Long.toString(System.nanoTime()));
        
        if (!codeDir.delete() || !codeDir.mkdir()) {
            throw new IOException("Failed to create code directory: " + codeDir.getAbsolutePath()); 
        }
    }
    
    @After public void clean() {
        codeDir.delete();
    }
    
    @Test public void
    generatesHtmlFromNarrativeStyleTest() {
        
         AuthorActor author = new AuthorActor();
         Given.the(author).was_able_to(write_a_simple_narrative_test_in(codeDir));
         
         When.the(author).attempts_to(produce_a_human_readable_version_of_the_test());
         
         Then.the(author).expects_that(the_output())
                         .should_be(a_readable_form_of_the_narrative());
    }
    
    private Action<ScriptWriter, AuthorActor> write_a_simple_narrative_test_in(final File codeDir) {
        return new Action<ScriptWriter, AuthorActor>() {
            @Override
            public void performFor(AuthorActor actor) {
                actor.writeSomeCodeIn(codeDir);
            }
        };
    }

    private Action<ScriptWriter, AuthorActor> produce_a_human_readable_version_of_the_test() {
        return new Action<ScriptWriter, AuthorActor>() {
            @Override
            public void performFor(AuthorActor author) {
                ScriptWriter.main(new String[] { author.getCodePath() });
            }
        };
    }

    private Extractor<String, AuthorActor> the_output() {
        return new Extractor<String, AuthorActor>() {
            @Override
            public String grabFor(AuthorActor author) {
                if (!author.getOutputPath().exists()) {
                   throw new RuntimeException("Output directory was not created.");
                }
                
                File firstOutput = FileUtils.iterateFiles(author.getOutputPath(), new String[] { "html" }, false).next();
                
                try {
                    return FileUtils.readFileToString(firstOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                return null;
            }
        }; 
    }

    private Matcher<String> a_readable_form_of_the_narrative() {
        return allOf(containsString("<html>"),
                     containsString("BasicArithmeticTest"),
                     containsString("the operator was able to"));
    }

    public static class AuthorActor implements Actor<ScriptWriter, AuthorActor> {
        private File codeDirectory;

        @Override
        public ScriptWriter tool() {
            return null;
        }

        public File getOutputPath() {
            return new File(codeDirectory, "output");
        }

        public String getCodePath() {
            return codeDirectory.getAbsolutePath();
        }

        public void writeSomeCodeIn(File codeDir) {
            this.codeDirectory = codeDir;
            try {
                FileUtils.copyFileToDirectory(new File("scriptwriter2/src/test/fixtures/BasicArithmeticTest.java"), codeDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void perform(Action<ScriptWriter, AuthorActor> action) {
            action.performFor(this);
        }

        @Override
        public <DATA> DATA grabUsing(Extractor<DATA, AuthorActor> extractor) {
            return extractor.grabFor(this);
        }
    }
    
    public static class ScriptWriter {
        public static void main(String[] args) {
            File outputDir = new File(args[0], "output");
            outputDir.mkdir();
            
            File output = new File(outputDir, "t.html");
            try {
                FileUtils.writeStringToFile(output, "Hello");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
