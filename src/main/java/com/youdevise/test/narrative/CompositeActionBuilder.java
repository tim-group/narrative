package com.youdevise.test.narrative;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CompositeActionBuilder<T> {
    private final List<Action<T>> children = new ArrayList<Action<T>>();
    private final ActionHandler<T> handler;
    
    public static <T> CompositeActionBuilder<T> of(Action<T> firstChild) {
        return new CompositeActionBuilder<T>(firstChild);
    }
    
    public static <T> CompositeActionBuilder<T> forActionsOf(Class<T> actionType) {
        return new CompositeActionBuilder<T>(new Action<T>() {
            @Override public void performFor(T tool, Stash stash) { }
        });
    }
    
    private CompositeActionBuilder(Action<T> child) { 
        children.add(child);
        this.handler = new ActionHandler<T>() {
            @Override public void handle(Action<T> action, T tool, Stash stash) { }
        };
    }
    
    private CompositeActionBuilder(CompositeActionBuilder<T> previous, Action<T> child) {
        children.addAll(previous.children);
        children.add(child);
        this.handler = previous.handler;
    }
    
    private CompositeActionBuilder(CompositeActionBuilder<T> previous, ActionHandler<T> handler) {
        children.addAll(previous.children);
        this.handler = handler;
    }
    
    public List<Action<T>> getChildren() {
        return unmodifiableList(children);
    }
    
    public CompositeActionBuilder<T> andThen(Action<T> nextChild) {
        return new CompositeActionBuilder<T>(this, nextChild);
    }

    public Action<T> build() {
        return new Action<T>() {
            @Override
            public void performFor(T tool, Stash stash) {
                for (Action<T> action : getChildren()) {
                    handler.handle(action, tool, stash);
                    action.performFor(tool, stash);
                }
            }
        };
    }

    public CompositeActionBuilder<T> beforeEach(ActionHandler<T> actionHandler) {
        return new CompositeActionBuilder<T>(this, actionHandler);
    }

}