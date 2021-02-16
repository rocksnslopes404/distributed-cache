package com.weta.interview.models.cache;

import java.util.*;

/**
 * TODO:  Implement connectors to MemCache, use internal Map for now
 */
public class MemCache implements Cache {

    Map<Object, Object> mockCache = new HashMap<Object, Object>();

    @Override
    public Boolean put(Object key, Object value) {
        // TODO:  Connect to Memcache using predefined client
        mockCache.put(key, value);
        return true;
    }

    @Override
    public Object get(Object key) {
        // TODO:  Connect to Memcache using predefined client
        return mockCache.get(key);
    }

    @Override
    public Boolean removeAll() {
        // TODO:  Connect to Memcache using predefined client
        mockCache.clear();
        return true;
    }

    @Override
    public Boolean remove(Object key) {
        // TODO:  Connect to Memcache using predefined client
        mockCache.remove(key);
        return true;
    }

    @Override
    public Set<Object> keys() {
        // TODO:  Connect to Memcache using predefined client
        return mockCache.keySet();
    }

    @Override
    public Collection<Object> values() {
        // TODO:  Connect to Memcache using predefined client
        return mockCache.values();
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        // TODO:  Connect to Memcache using predefined client
        return mockCache.entrySet();
    }
}
