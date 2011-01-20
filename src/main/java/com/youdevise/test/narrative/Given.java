package com.youdevise.test.narrative;

/**
 * Declaration of the initial state that the system should be in.
 *
 * @param <TOOL>
 *            The type of the tool used by the actor.
 * @param <ACTOR>
 *            The type of the actor.
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
        try {
            actor.perform(action);
        } catch(Throwable t) {
            throw new RuntimeException(t);
        }
        return this;
    }

    public Given<TOOL, ACTOR> and_to(Action<TOOL, ACTOR> action) {
        return was_able_to(action);
	}
}
