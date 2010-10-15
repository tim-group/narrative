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

    public <S> Given<S> and_the(Actor<S> actor) {
        return new Given<S>(actor);
    }

    public Given<T> was_able_to(Action<T> action) {
        actor.perform(action);
        return this;
    }
}