package com.youdevise.test.narrative;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MatchingExceptionsTest {
    ExceptionActor actor = new ExceptionActor();

    @Test public void
    can_catch_and_match_against_exceptions() {
        Given.the(actor).was_able_to(remember_the_error_from(do_a_messy_action()));
        Then.the(actor).expects_that(the_exception_thrown(), is(the_app_failure_exception()));
    }

    private Throwable the_app_failure_exception() {
        return new AppSpecificException("Our app failed.");
    }

    private Action<Object, ExceptionActor> remember_the_error_from(final Action<Object, ExceptionActor> action) {
        return new Action<Object, ExceptionActor>() {
            @Override
            public void performFor(ExceptionActor actor) throws Throwable {
                try {
                    action.performFor(actor);
                } catch(AppSpecificException e) {
                    actor.remember("exception", e);
                }
            }
        };
    }

    private Action<Object, ExceptionActor> do_a_messy_action() {
        return new Action<Object, ExceptionActor>() {
            @Override
            public void performFor(ExceptionActor actor) throws Throwable {
                throw new AppSpecificException("Our app failed.");
            }
        };
    }


    private Extractor<Throwable, ExceptionActor> the_exception_thrown() {
        return new Extractor<Throwable, ExceptionActor>() {
            @Override public Throwable grabFor(ExceptionActor actor) {
                return actor.recall("exception", Throwable.class);
            }
        };
    }

    private static class ExceptionActor implements ActorWithMemory<Object, ExceptionActor> {
        private final Map<Object, Object> memory = new HashMap<Object, Object>();

        @Override
        public <DATA> DATA grabUsing(Extractor<DATA, ExceptionActor> extractor) {
            return extractor.grabFor(this);
        }

        @Override public void perform(Action<Object, ExceptionActor> action) throws Throwable {
            action.performFor(this);
        }

        @Override public Object tool() { return null; }

        @SuppressWarnings("unchecked")
        @Override
        public <MEMORY> MEMORY recall(Object identifier, Class<MEMORY> type) {
            return (MEMORY)memory.get(identifier);
        }

        @Override
        public void remember(Object identifier, Object value) {
            memory.put(identifier, value);
        }
    }

    private static class AppSpecificException extends Exception {
        private static final long serialVersionUID = 1L;

        public AppSpecificException(String message) {
            super(message);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return getMessage().equals(((AppSpecificException)obj).getMessage());
        }
    }
}
