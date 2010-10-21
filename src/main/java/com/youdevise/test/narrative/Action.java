package com.youdevise.test.narrative;

/**
 * An action that can be performed by an Actor that changes the state of the system.
 * @param <T> The type of the tool used by the Actor.
 */
public interface Action<ACTOR extends Actor<?>> {
    void performFor(ACTOR actor);
}