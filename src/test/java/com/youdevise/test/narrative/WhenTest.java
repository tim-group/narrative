package com.youdevise.test.narrative;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JMock.class)
public class WhenTest {
    private Mockery context = new Mockery();

    @SuppressWarnings("unchecked")
    @Test public void
    usesTheActorToPerformTheAction() throws Throwable {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> action = context.mock(Action.class);

        context.checking(new Expectations() {{
            oneOf(actor).perform(action);
        }});

        When.the(actor).attempts_to(action);
    }

    @SuppressWarnings("unchecked")
    @Test public void
    canPerformanManyActionsInARow() throws Throwable {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> action = context.mock(Action.class, "action");
        final Action<String, StringActor> otherAction = context.mock(Action.class, "other action");
        
        final Sequence orderOfActions = context.sequence("Order of the actions");
        context.checking(new Expectations() {{
            oneOf(actor).perform(action); inSequence(orderOfActions);
            oneOf(actor).perform(otherAction); inSequence(orderOfActions);
        }});
        
        When.the(actor).attempts_to(action)
                       .and_to(otherAction);
    }

    @SuppressWarnings("unchecked")
    @Test public void
    canMixCallsTo_AttempsTo_AndTo_AndTo() throws Throwable {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> firstAction = context.mock(Action.class, "first action");
        final Action<String, StringActor> secondAction = context.mock(Action.class, "second action");
        final Action<String, StringActor> thirdAction = context.mock(Action.class, "third action");
        final Action<String, StringActor> fourthAction = context.mock(Action.class, "fourth action");
        
        final Sequence orderOfActions = context.sequence("Order of the actions");
        context.checking(new Expectations() {{
            oneOf(actor).perform(firstAction); inSequence(orderOfActions);
            oneOf(actor).perform(secondAction); inSequence(orderOfActions);
            oneOf(actor).perform(thirdAction); inSequence(orderOfActions);
            oneOf(actor).perform(fourthAction); inSequence(orderOfActions);
        }});
        
        When.the(actor).and_to(firstAction)
            .and_to(secondAction)
            .and_to(thirdAction)
            .attempts_to(fourthAction);
    }

    @SuppressWarnings("unchecked")
    @Test public void
    canRethrowExceptionsFromActorsAsUncheckedExceptions() throws Throwable {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> action = context.mock(Action.class, "first action");
        final Throwable originalException = new Throwable("Something broke");

        context.checking(new Expectations() {{
                allowing(actor).perform(action);
                will(throwException(originalException));
        }});

        try {
            When.the(actor).attempts_to(action);
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), containsString("Something broke"));
            assertThat(e.getCause(), equalTo(originalException));
        }
    }
}
