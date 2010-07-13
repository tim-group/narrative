package com.youdevise.test.narrative;

/**
 * Action declaration of what the Actor does to achieve the final desired result, which is then checked with a Then statement.
 * @param <T> The type of the tool the Actor uses
 */
public class When<T> {
    private final Actor<T> actor;

    private When(Actor<T> actor) {
        this.actor = actor;
    }

    public static <T> When<T> the(Actor<T> actor) {
        return new When<T>(actor);
    }
    
    public When<T> attemptsTo(Action<T> action) {
        actor.perform(action);
        return this;
    }
}