package com.weta.interview.cache;

import java.util.*;

/**
 * TODO:  Implement connectors to Redis, return mock objects for now
 */
public class RedisCache implements Cache {

    Map<Object, Object> mockCache = new HashMap<Object, Object>();

    @Override
    public Boolean put(Object key, Object value) {
        // TODO:  Connect to Redis using predefined client
        mockCache.put(key, value);
        return true;
    }

    @Override
    public Object get(Object key) {
        // TODO:  Connect to Redis using predefined client
        return mockCache.get(key);
    }

    @Override
    public Boolean removeAll(List<Object> keys) {
        mockCache.clear();
        return true;
    }

    @Override
    public Set<Object> keys() {
        return mockCache.keySet();
    }

    @Override
    public Collection<Object> values() {
        return mockCache.values();
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        return mockCache.entrySet();
    }
}
