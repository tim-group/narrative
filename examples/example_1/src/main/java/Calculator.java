package com.youdevise.narrative.example;

import jline.ConsoleReader;

public class Calculator {
    private static enum OPERATOR {
        PLUS {
            public int apply(int accumulator, int value) {
                return accumulator + value;
            }
        }, 
        MINUS {
            public int apply(int accumulator, int value) {
                return accumulator - value;
            }
        }, 
        EQUALS {
            public int apply(int accumulator, int value) {
                return accumulator;
            }
        };

        public abstract int apply(int accumulator, int value);

        public static OPERATOR lookup(char keypress) {
            if (keypress == '+') { return PLUS; }
            if (keypress == '-') { return MINUS; }
            if (keypress == '=') { return EQUALS; }
            return null;
        }
    }

    private int accumulator = 0;
    private OPERATOR currentOperator = OPERATOR.PLUS;

    public void press(char keypress) {
        if (Character.isDigit(keypress)) {
            accumulator = currentOperator.apply(accumulator, Character.digit(keypress, 10));
        } else {
            currentOperator = OPERATOR.lookup(keypress);
        }
    }

    public String read() {
        return String.valueOf(accumulator);
    }
    
    public static void main(String args[]) throws Throwable {
        char[] allowed = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '='};
        Calculator calculator = new Calculator();

        System.out.println("Welcome to the calculator");

        ConsoleReader input = new ConsoleReader();
        while (true) {
            char keypress = (char) input.readCharacter(allowed);
            calculator.press(keypress);
            System.out.println("You pressed " + keypress + ". Display reads: " + calculator.read());
        }
    }
}
