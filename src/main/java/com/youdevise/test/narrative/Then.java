package com.youdevise.test.narrative;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A assertion of the final state of the system for a narrative
 * @param <T> The type of tool that the Actor uses
 */
public class Then<TOOL, ACTOR extends Actor<TOOL>> {
    private final ACTOR actor;

    /**
     * Type safe matching.
     * @param <D> The type of data that is going to be checked
     */
    public class TypedMatcher<DATA> {
        private final Extractor<DATA, ACTOR> expected;
        private final Then<TOOL, ACTOR> outer;

        public TypedMatcher(Extractor<DATA, ACTOR> expected, Then<TOOL, ACTOR> outer) {
            this.expected = expected;
            this.outer = outer;
        }
        
        public TypedMatcher<DATA> should_be(Matcher<? super DATA> matcher) {
            return should(matcher);
        }

        public TypedMatcher<DATA> should_have(Matcher<? super DATA> matcher) {
            return should(matcher);
        }

        public TypedMatcher<DATA> should(Matcher<? super DATA> matcher) {
            assertThat(actor.grabUsing(expected), matcher);
            return this;
        }

        public Then<TOOL, ACTOR> andAlso() {
            return outer;
        }

        public <EXTENDED> TypedMatcher<EXTENDED> and_also_expects_that(Extractor<EXTENDED, ACTOR> next_expected) {
            return outer.expects_that(next_expected);
        }
    }

    private Then(ACTOR actor) {
        this.actor = actor;
    }

    public <DATA> TypedMatcher<DATA> expects_that(Extractor<DATA, ACTOR> expected) {
        return new TypedMatcher<DATA>(expected, this);
    }

    public static <TOOL, ACTOR extends Actor<TOOL>> Then<TOOL, ACTOR> the(ACTOR actor) {
        return new Then<TOOL, ACTOR>(actor);
    }
}