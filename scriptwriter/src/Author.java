import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Author {
    public static void main(String[] args) throws Exception {
        String fileContent = slurpFile(args[0]);

        TestParser parser = new TestParser();
        TreeNode parseTree = parser.parse(fileContent);

        ScriptCompiler compiler = new ScriptCompiler();
        compiler.compile(parseTree);
    }

    private static String slurpFile(String fileName) throws IOException {
        FileInputStream stream = new FileInputStream(new File(fileName));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            stream.close();
        }
    }
}
