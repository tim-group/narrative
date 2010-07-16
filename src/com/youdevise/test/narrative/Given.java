package com.youdevise.test.narrative;

/**
 * Declaration of the initial state that the system should be in.
 * @param <T> The type of tool used by the Actor.
 */
public class Given<T> {
    private final Actor<T> actor;

    private Given(Actor<T> actor) {
        this.actor = actor;
    }

    public static <T> Given<T> the(Actor<T> actor) {
        return new Given<T>(actor);
    }

    public Given<T> wasAbleTo(Action<T> action) {
        actor.perform(action);
        return this;
    }
    
    public Given<T> was_able_to(Action<T> action) {
        return wasAbleTo(action);
    }
}