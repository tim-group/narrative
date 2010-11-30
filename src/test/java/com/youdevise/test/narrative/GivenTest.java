package com.youdevise.test.narrative;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JMock.class)
public class GivenTest {
    private Mockery context = new Mockery();
    
    @SuppressWarnings("unchecked")
    @Test public void
    usesTheActorToPerformTheAction() {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> action = context.mock(Action.class);
        
        context.checking(new Expectations() {{
            oneOf(actor).perform(action);
        }});
        
        Given.the(actor).was_able_to(action);
    }
    
    @SuppressWarnings("unchecked")
    @Test public void
    canPerformManyActionsInARow() {
        final StringActor actor = context.mock(StringActor.class);
        final Action<String, StringActor> action = context.mock(Action.class, "first action");
        final Action<String, StringActor> otherAction = context.mock(Action.class, "second action");
        
        final Sequence orderOfActions = context.sequence("Order of the actions");
        context.checking(new Expectations() {{
            oneOf(actor).perform(action); inSequence(orderOfActions);
            oneOf(actor).perform(otherAction); inSequence(orderOfActions);
        }});
        
        Given.the(actor).was_able_to(action)
            .and_to(otherAction);
    }
    
    @SuppressWarnings("unchecked")
    @Test public void
    canMixCallsTo_WasAbleTo_AndTo_AndTo() {
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
        
        Given.the(actor).and_to(firstAction)
            .was_able_to(secondAction)
            .and_to(thirdAction)
            .was_able_to(fourthAction);
    }
}
