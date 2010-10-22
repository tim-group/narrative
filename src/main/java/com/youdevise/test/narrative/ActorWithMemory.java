package com.youdevise.test.narrative;

/**
 * An actor that can remember things.
 * 
 * @param <TOOL> The type of tool that the Actor uses
 */
public interface ActorWithMemory<TOOL, IMPL extends Actor<TOOL, IMPL>> extends Actor<TOOL, IMPL> {
	void remember(Object identifier, Object value);
	<MEMORY> MEMORY recall(Object identifier, Class<MEMORY> type);
}
