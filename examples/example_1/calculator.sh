CLASSES_DIRECTORY="build/classes/main"
JLINE_JAR="build/lib/jline-0.9.1.jar"
CLASSPATH="$CLASSES_DIRECTORY:$JLINE_JAR"
CONSOLE_CALCULATOR_CLASS_FILE=$CLASSES_DIRECTORY/"com/youdevise/narrative/example/ConsoleCalculator.class"
CONSOLE_CALCULATOR_CLASS="com.youdevise.narrative.example.ConsoleCalculator"

if [ ! -f $CONSOLE_CALCULATOR_CLASS_FILE ]
then
    echo "ERROR: Calculator class file not found."
    echo "Expected to find this file at $CONSOLE_CALCULATOR_CLASS_FILE."
    echo "Try gradle :build." 
else
    java -cp $CLASSPATH $CONSOLE_CALCULATOR_CLASS
fi
