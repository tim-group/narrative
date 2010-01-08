package com.youdevise.test.narrative;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


public class CompositeActionBuilderTest {
    private Mockery context = new Mockery();
    
    @SuppressWarnings("unchecked")
    @Test public void
    performsEachComponentActionInTurn() {
        final Action<Object> child1 = context.mock(Action.class, "first child");
        final Action<Object> child2 = context.mock(Action.class, "second child");
        final Object tool = new Object();
        final Stash stash = new HashMapStash();
        
        final Sequence ordering = context.sequence("Order in which children are called"); 
        context.checking(new Expectations() {{
            oneOf(child1).performFor(tool, stash); inSequence(ordering);
            oneOf(child2).performFor(tool, stash); inSequence(ordering);
        }});
        
        CompositeActionBuilder<Object> builder = new CompositeActionBuilder().of(child1).andThen(child2);
        builder.build().performFor(tool, stash);
        
        context.assertIsSatisfied();
    }
    
    @SuppressWarnings("unchecked")
    @Test public void
    canHookIntoBeforeTheActionCall() {
        final Action<Object> child1 = context.mock(Action.class, "first child");
        final Action<Object> child2 = context.mock(Action.class, "second child");
        final Object tool = new Object();
        final Stash stash = new HashMapStash();
        final List<Action<Object>> handled = new ArrayList<Action<Object>>();
        
        context.checking(new Expectations() {{
            ignoring(child1);
            ignoring(child2);
        }});
        
        CompositeActionBuilder<Object> builder = new CompositeActionBuilder().beforeEach(new ActionHandler<Object>() {
            @Override public void handle(Action<Object> action, Object tool, Stash stash) {
                handled.add(action);
            }
        }).of(child1).andThen(child2);
        builder.build().performFor(tool, stash);
        
        assertThat(handled, contains(child1, child2));
    }
}
