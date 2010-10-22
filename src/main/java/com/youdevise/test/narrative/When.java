package com.youdevise.test.narrative;

/**
 * Action declaration of what the Actor does to achieve the final desired result, which is then checked with a Then statement.
 * @param <TOOL> The type of the tool the Actor uses
 * @param <ACTOR> The type of the Actor
 */
public class When<TOOL, ACTOR extends Actor<TOOL, ACTOR>> {
    private final ACTOR actor;

    private When(ACTOR actor) {
        this.actor = actor;
    }

    public static <T, A extends Actor<T, A>> When<T, A> the(A actor) {
        return new When<T, A>(actor);
    }

    public <T, A extends Actor<T, A>> When<T, A> and_the(A actor) {
        return new When<T, A>(actor);
    }

    public When<TOOL, ACTOR> attempts_to(Action<TOOL, ACTOR> action) {
        actor.perform(action);
        return this;
    }
}
