package com.youdevise.test.narrative;

/**
 * Declaration of the initial state that the system should be in.
 * @param <TOOL> The type of tool used by the Actor.
 * @param <ACTOR> The type of Actor.
 */
public class Given<TOOL, ACTOR extends Actor<TOOL, ACTOR>> {
    private final ACTOR actor;

    private Given(ACTOR actor) {
        this.actor = actor;
    }

    public static <TOOL, ACTOR extends Actor<TOOL, ACTOR>> Given<TOOL, ACTOR> the(ACTOR actor) {
        return new Given<TOOL, ACTOR>(actor);
    }

    public <TOOL2, ACTOR2 extends Actor<TOOL2, ACTOR2>> Given<TOOL2, ACTOR2> and_the(ACTOR2 actor) {
        return new Given<TOOL2, ACTOR2>(actor);
    }

    public Given<TOOL, ACTOR> was_able_to(Action<TOOL, ACTOR> action) {
        actor.perform(action);
        return this;
    }
}
