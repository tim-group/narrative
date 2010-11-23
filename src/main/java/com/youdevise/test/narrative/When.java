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

    public static <TOOL, ACTOR extends Actor<TOOL, ACTOR>> When<TOOL, ACTOR> the(ACTOR actor) {
        return new When<TOOL, ACTOR>(actor);
    }

    public <TOOL2, ACTOR2 extends Actor<TOOL2, ACTOR2>> When<TOOL2, ACTOR2> and_the(ACTOR2 actor) {
        return new When<TOOL2, ACTOR2>(actor);
    }

    public When<TOOL, ACTOR> attempts_to(Action<TOOL, ACTOR> action) {
        actor.perform(action);
        return this;
    }
}
