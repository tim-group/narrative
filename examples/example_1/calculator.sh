CLASSES_DIRECTORY="build/classes/main"
JLINE_JAR="build/lib/jline-0.9.1.jar"
CLASSPATH="$CLASSES_DIRECTORY:$JLINE_JAR"
CALCULATOR_CLASS_FILE=$CLASSES_DIRECTORY/"com/youdevise/narrative/example/Calculator.class"
CALCULATOR_CLASS="com.youdevise.narrative.example.Calculator"

if [ ! -f $CALCULATOR_CLASS_FILE ]
then
    echo "ERROR: Calculator class file not found. Try gradle :build." 
else
    java -cp $CLASSPATH $CALCULATOR_CLASS
fi
