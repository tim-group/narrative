package com.youdevise.narrative.example;

import jline.ConsoleReader;
   
public class ConsoleCalculator {
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
