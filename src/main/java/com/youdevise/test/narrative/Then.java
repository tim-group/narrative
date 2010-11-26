package com.youdevise.test.narrative;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A assertion of the final state of the system for a narrative
 *
 * @param <TOOL>
 *            The type of the tool used by the actor.
 * @param <ACTOR>
 *            The type of the actor.
 */
public class Then<TOOL, ACTOR extends Actor<TOOL, ACTOR>> {
    private final ACTOR actor;

    private Then(ACTOR actor) {
        this.actor = actor;
    }

    public static <TOOL, ACTOR extends Actor<TOOL, ACTOR>> Then<TOOL, ACTOR> the(ACTOR actor) {
        return new Then<TOOL, ACTOR>(actor);
    }

    public <TOOL2, ACTOR2 extends Actor<TOOL2, ACTOR2>> Then<TOOL2, ACTOR2> and_the(ACTOR2 actor) {
        return the(actor);
    }

    public <DATA> Then<TOOL, ACTOR> expects_that(Extractor<DATA, ACTOR> expected, Matcher<? super DATA> matcher) {
        assertThat(actor.grabUsing(expected), matcher);
        return this;
    }
}
