import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ScriptCompiler {
    public ScriptCompiler() { }

    public void compile(TreeNode root) throws IOException {
        String title = getTitle(root);

        new File("output").mkdir();
        Writer out = new FileWriter("output/" + title + ".html");

        try {
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
        } finally {
            out.close();
        }
    }

    public String getTitle(TreeNode root) {
        TreeNode classDeclarationNode = root.getFirstChildByName("typeDeclaration")
                                            .getFirstChildByName("classOrInterfaceDeclaration")
                                            .getFirstChildByName("classDeclaration")
                                            .getFirstChildByName("normalClassDeclaration");
        for (TreeNode child : classDeclarationNode.children) {
            if (child.isLiteral()) { return child.toString(); }
        }

        return "Unknown title";
    }

    public void visit(TreeNode node, Writer out) throws IOException {
        for (TreeNode child : node.children) {
            if (isTestMethod(child)) {
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

    public boolean isTestMethod(TreeNode node) {
        if (! node.text.equals("methodDeclaration")) { return false; }
        TreeNode modifiers      = node.         getFirstChildByName("modifiers");          if (null == modifiers)      { return false; }
        TreeNode annotation     = modifiers.    getFirstChildByName("annotation");         if (null == annotation)     { return false; }
        TreeNode qualifiedName  = annotation.   getFirstChildByName("qualifiedName");      if (null == qualifiedName)  { return false; }
        TreeNode testAnnotation = qualifiedName.getFirstChildByName("IDENTIFIER['Test']"); if (null == testAnnotation) { return false; }
        return true;
    }
}
