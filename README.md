# Narrative #

A framework for building behaviour-driven tests in fluent Java. Copyright 2010 [youDevise, Ltd.](http://www.youdevise.com).

# Installation #

Download [the Narrative jar file](http://github.com/downloads/youdevise/narrative/narrative-1.0.jar) and the [Hamcrest Matchers library (version 1.2)](http://code.google.com/p/hamcrest/downloads/detail?name=hamcrest-all-1.2.jar).  Then put both jars in your classpath where your test code can see them.

# Usage #

Here is a typical Narrative test (the object under test is a very simple calculator):

    @Test public void
    adds_two_numbers() {
        Given.the( operator).was_able_to( press('2'))
                            .was_able_to( press('+'))
                            .was_able_to( press('2'));

        When.the( operator).attempts_to( press('='));

        Then.the( operator).expects_that( the_displayed_value(), is("4"));
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

## Viewing in Eclipse ##

To view Narrative source code in Eclipse:

  1. Run `gradle :eclipse` (or `./gradlew :eclipse`). This downloads the libraries and creates the project and classpath files. 
  2. Create a new classpath variable in Preferences / Java / Build Path / Classpath Variables with the name `GRADLE_CACHE` and the value `<USER_HOME>/.gradle/cache` (we have not tested this on Windows, but we think it should work similarly).
  3. Import the project as normal.

# License #

Open source under the very permissive [MIT license](http://github.com/youdevise/narrative/blob/master/LICENSE).

# Acknowledgements #

A project of [youDevise](https://dev.youdevise.com). We're [hiring](http://www.youdevise.com/careers)!

Inspired by [JNarrate](http://JNarrate.org), a [RiverGlide](http://www.riverglide.com) idea.
