package com.weta.interview.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Cache {
    Boolean put(Object key, Object value);
    Object get(Object key);
    Boolean removeAll(List<Object> keys);
    Set<Object> keys();
    Collection<Object> values();
    Set<Map.Entry<Object, Object>> entrySet();
}
