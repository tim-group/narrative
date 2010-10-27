# Narrative #

A framework for building behaviour-driven tests in fluent Java. Copyright 2010 [youDevise, Ltd.](http://www.youdevise.com).

# Installation #

Download [the Narrative jar file](http://github.com/downloads/youdevise/narrative/narrative-0.8.jar) and put it in your classpath where your test code can see it.

# Usage #

Here's a typical Narrative test (the object under test is a very simple calculator):

    @Test public void
    adds_two_numbers() {
        Given.the( operator).was_able_to( press('2'))
                            .was_able_to( press('+'))
                            .was_able_to( press('2'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value()).should_be( equalTo("4"));
    }

To make this test work, you need to define:

1. `operator`, an `Actor` which manipulates the calculator
2. `press()` and `the_displayed_value()`, which tell the `operator` to perform actions on the calculator

You can see these implementations in [our most basic example](http://github.com/youdevise/narrative/blob/master/examples/example_1/src/test/java/BasicArithmeticTest.java). We plan to add more examples in the `examples` directory of the source code.

# Building from source #

We build using [Gradle](http://www.gradle.org/). But you don't need Gradle installed to build Narrative.

## If you don't have Gradle installed ##

In the Narrative home directory, type `./gradlew` (on Linux and Mac) or `gradlew.bat` (on Windows). This will build, run tests, and put `Narrative.jar` in `build/libs`.

## If you do have Gradle installed, and in your path ##

Use `gradle` to build, run tests, and produce the `Narrative.jar` in `build/libs`.

## Acknowledgements ##

A project of [youDevise](https://dev.youdevise.com). We're [hiring](http://www.youdevise.com/careers)!

Inspired by [JNarrate](http://JNarrate.org), a [RiverGlide](http://www.riverglide.com) idea.
