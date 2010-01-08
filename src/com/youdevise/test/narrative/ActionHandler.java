package com.youdevise.test.narrative;

/**
 * 
 */
public interface ActionHandler<T> {
    void handle(Action<T> action, T tool, Stash stash);
}
