# Basic example for Narrative #

Narrative is a framework for building behaviour-driven tests in fluent Java. Copyright 2010 [youDevise, Ltd.](http://www.youdevise.com).

# Introduction #

This example is an implementation of an extremely simple calculator. Its acceptance tests demonstrate some of the most
basic features of Narrative.

# Building the example #

We build using [Gradle](http://www.gradle.org/). But you don't need Gradle installed to build Narrative.

## If you don't have Gradle installed ##

In the `examples/example_1` directory, type `../../gradlew` (on Linux and Mac) or `..\..\gradlew.bat` (on Windows). This will build and run tests.

## If you do have Gradle installed, and in your path ##

Use `gradle` to build and run tests.

# Running the calculator #

You can run the calculator interactively. 
In a console window, go to the `examples/example_1` directory and type `calculator.sh` (Linux and Mac) or `calculator.bat` (Windows).
After the calculator prints a welcome message, it waits for your keypresses and updates its display accordingly. 
For example, try pushing `2`, then `+`, then `2`.

# Looking at the tests #

Have a look at `/examples/example_1/src/test/java/BasicArithmeticTest.java` to see the acceptance tests for the calculator
and the Java methods and classes that make them work. Comments in the file should help you understand how to create and use 
`Actor`s and how to give them work to do.

