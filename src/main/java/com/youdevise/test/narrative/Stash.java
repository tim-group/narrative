package com.youdevise.test.narrative;

public interface Stash {
    <T> T get(Object key, Class<T> type);
    void put(Object key, Object value);
    boolean contains(Object key);
}
