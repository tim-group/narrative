package com.youdevise.test.narrative;

/**
 * Actor interface used for testing to get rid of generics showing 
 * up all over the tests and causing problems.
 */
public interface StringActor extends Actor<String, StringActor> { }