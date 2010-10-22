package com.youdevise.test.narrative;

/**
 * An entity performing actions during a narrative.
 * @param <T> the type of tool the Actor uses to perform actions
 */
public interface Actor<TYPE> {
	TYPE tool();
	
    void perform(Action<TYPE, ? extends Actor<TYPE>> action);
    <D> D grabUsing(Extractor<D, ? extends Actor<TYPE>> extractor);
}