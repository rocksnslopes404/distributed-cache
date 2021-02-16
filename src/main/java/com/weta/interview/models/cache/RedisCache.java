package com.weta.interview.models.cache;

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
    public Boolean removeAll() {
        // TODO:  Connect to Redis using predefined client
        mockCache.clear();
        return true;
    }

    @Override
    public Boolean remove(Object key) {
        // TODO:  Connect to Redis using predefined client
        mockCache.remove(key);
        return true;
    }

    @Override
    public Set<Object> keys() {
        // TODO:  Connect to Redis using predefined client
        return mockCache.keySet();
    }

    @Override
    public Collection<Object> values() {
        // TODO:  Connect to Redis using predefined client
        return mockCache.values();
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        // TODO:  Connect to Redis using predefined client
        return mockCache.entrySet();
    }
}
