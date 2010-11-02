To build, type
ant dist

To run, type
java -jar dist/scriptwriter.jar [name of file to parse]

----Original README-------

This is a visual tool showing how a java file is parsed. 

This tool is based on the java grammar (http://openjdk.java.net/projects/compiler-grammar/antlrworks/Java.g) published on 
OpenJDK Compiler Grammar project (http://openjdk.java.net/projects/compiler-grammar). Only a few changes were made in order 
to automatically process this file.

To build this project, download the latest ANTLR library from http://antlr.org/download.html. You need the 
Complete ANTLR *.*.* jar file, then store it under a "lib" directory. Supposing you put the antlr.*.*.*.jar under /home/username/lib/

then go to the project directory and run :
    ant -f build.xml -Dantlr.home=/home/username

This will automatically build the project and start the GUI.
Then, later if you want to run the GUI again, run
    java -jar tree.jar
    
PS. I'm not a GUI master, feedbacks and improvements are welcome, feel free to change the sources.

Yang Jiang
yang.jiang.z@gmail.com

Nov. 30, 2008 




