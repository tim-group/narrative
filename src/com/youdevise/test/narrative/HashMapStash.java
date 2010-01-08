package com.youdevise.test.narrative;

import java.util.HashMap;
import java.util.Map;

public class HashMapStash implements Stash {
    private final Map<Object, Object> store = new HashMap<Object, Object>();

    @Override
    public boolean contains(Object key) {
        return store.containsKey(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (contains(key) && !type.isInstance(store.get(key))) {
            throw new ClassCastException();
        }
        
        return (T)store.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        store.put(key, value);
    }
}
