package com.youdevise.test.narrative;

/**
 * An entity performing actions during a narrative.
 * @param <T> the type of tool the Actor uses to perform actions
 */
public interface Actor<T> {
    void perform(Action<T> action);
    <D> D grabUsing(Extractor<D, T> extractor);
}