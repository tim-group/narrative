package com.youdevise.test.narrative;

/**
 * Declaration of the initial state that the system should be in.
 * @param <TOOL> The type of tool used by the Actor.
 * @param <ACTOR> The type of Actor.
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

    public Given<TOOL, ACTOR> was_able_to(Action<TOOL, ACTOR> action) {
        actor.perform(action);
        return this;
    }
}