package com.youdevise.test.narrative;

/**
 * An action that can be performed by an Actor that changes the state of the
 * system.
 *
 * @param <TOOL>
 *            The type of the tool used by the Actor.
 */
public interface Action<TOOL, ACTOR extends Actor<TOOL, ?>> {
    void performFor(ACTOR actor);
}
