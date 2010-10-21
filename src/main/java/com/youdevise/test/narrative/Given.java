package com.youdevise.test.narrative;

/**
 * Declaration of the initial state that the system should be in.
 * @param <T> The type of tool used by the Actor.
 */
public class Given<TOOL, ACTOR extends Actor<TOOL>> {
    private final ACTOR actor;

    private Given(ACTOR actor) {
        this.actor = actor;
    }

    public static <T, A extends Actor<T>> Given<T, A> the(A actor) {
        return new Given<T, A>(actor);
    }

    public <T, A extends Actor<T>> Given<T, A> and_the(A actor) {
        return new Given<T, A>(actor);
    }

    public Given<TOOL, ACTOR> was_able_to(Action<ACTOR> action) {
        actor.perform(action);
        return this;
    }
}