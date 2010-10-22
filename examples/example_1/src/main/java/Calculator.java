package com.youdevise.narrative.example;

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

        public static OPERATOR lookup(String string) {
            if (string.equals("+")) { return PLUS; }
            if (string.equals("-")) { return MINUS; }
            if (string.equals("=")) { return EQUALS; }
            return null;
        }
    }

    private int accumulator = 0;
    private OPERATOR currentOperator = OPERATOR.PLUS;

    public void enter(String value) {
        if (isNumber(value)) {
            accumulator = currentOperator.apply(accumulator, Integer.parseInt(value));
        } else {
            currentOperator = OPERATOR.lookup(value);
        }
    }

    public String read() {
        return String.valueOf(accumulator);
    }

    private boolean isNumber(String value) {
        return value.matches("\\d+");
    }
}
