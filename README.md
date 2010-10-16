# Narrative #

A framework for building behaviour-driven tests in fluent Java. Copyright 2010 [youDevise, Ltd.](http://www.youdevise.com).

# Installation #

Download [Narrative.jar](http://github.com/downloads/youdevise/narrative/Narrative.jar) and put it in your classpath where your test code can see it.

# Usage #

*simple example to be added here*

For more examples, see the examples directory.

# Building from source #

We build using [Gradle](http://www.gradle.org/). But you don't need Gradle installed to build Narrative.

## If you don't have Gradle installed ##

In the Narrative home directory, type `./gradlew` (on Linux and Mac) or `gradlew.bat` (on Windows). This will build, run tests, and put `Narrative.jar` in `build/libs`.

## If you do have Gradle installed ##

Use `gradle :test` to build and run tests, and `gradle :jar` to produce `Narrative.jar` in `build/libs`.