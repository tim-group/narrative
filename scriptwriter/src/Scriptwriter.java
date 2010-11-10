import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Scriptwriter {
    String title = "Temp title - get from class name";
    TreeNode root;
    
    public static void main(String[] args) throws Exception {
        String fileContent = slurpFile(args[0]);
        TestParser parser = new TestParser();
        TreeNode parseTree = parser.parse(fileContent);
        Scriptwriter scriptwriter = new Scriptwriter(parseTree);

        new File("output").mkdir();
        Writer out = new FileWriter("output/" + scriptwriter.title + ".html");
        scriptwriter.print(out);
        out.close();
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

    public Scriptwriter(TreeNode root) throws Exception {
        this.root = root;
    }

    public void print(Writer out) throws IOException {
        out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n");
        out.write("<html><head><title>" + title + "</title>\n"
                + "<link rel='stylesheet' type='text/css' href='http://yui.yahooapis.com/combo?2.8.1/build/reset/reset-min.css'>\n"
                + "<link href='http://fonts.googleapis.com/css?family=Cantarell&amp;subset=latin' rel='stylesheet' type='text/css'>\n"
                + "<link href='http://alexgorbatchev.com/pub/sh/current/styles/shThemeDefault.css' rel='stylesheet' type='text/css'>\n"
                + "<script src='http://alexgorbatchev.com/pub/sh/current/scripts/shCore.js' type='text/javascript'></script>\n"
                + "<script src='http://alexgorbatchev.com/pub/sh/current/scripts/shAutoloader.js' type='text/javascript'></script>\n"
                + "<link rel='stylesheet' type='text/css' href='narrative.css'>\n"
                + "</head>\n<body>\n");
        out.write("<div class='heading'><h1>" + title + "</h1></div>\n<div class='body'>\n<p>");
        visit(root, out);
        out.write("</p>\n</div></body></html>");
    }

    public void visit(TreeNode node, Writer out) throws IOException {
        for (TreeNode child : node.children) {
            if (child.isTestMethod()) {
                print(child, 0, out);
            } else {
                visit(child, out);
            }
        }
    }

    public void print(TreeNode node, int level, Writer out) throws IOException {
//for (int i=0; i<level*2; i++) { System.out.print(" "); }
//out.write(node.toString());
        printNode(node, level, out);
        for (TreeNode child : node.children) {
            if (child.text.equals("modifiers")) { continue; }
            print(child, level+1, out);
        }
    }

    public void printNode(TreeNode node, int level, Writer out) throws IOException {
        if (!node.isLiteral()) { return; }

        String output = prettyOutput(node);
        if (1 == level) { 
            output = "</p>\n<h2>" + output + "</h2>\n<p>"; 
        } 
        if (output.matches("Given |When |Then ")) { 
            output = "</p>\n<p><span class='verb'>" + output + "</span> "; 
        }

        out.write(output);
    }

    public String prettyOutput(TreeNode node) {
        String output = node.toString();
        output = output.replace('_', ' ');
        output = output.replace("equalTo", "equal to");
        return output + " ";
    }
}
