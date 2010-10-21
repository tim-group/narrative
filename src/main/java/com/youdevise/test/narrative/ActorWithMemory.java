package com.youdevise.test.narrative;

public interface ActorWithMemory<TOOL> extends Actor<TOOL> {
	void remember(Object identifier, Object value);
	<MEMORY> MEMORY recall(Object identifier, Class<MEMORY> type);
}
