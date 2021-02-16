package com.weta.interview.models.cache;

import com.weta.interview.exceptions.NetworkFailureException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Cache {
    Boolean put(Object key, Object value) throws NetworkFailureException;
    Object get(Object key) throws NetworkFailureException;
    Boolean removeAll() throws NetworkFailureException;
    Boolean remove(Object key) throws NetworkFailureException;
    Set<Object> keys() throws NetworkFailureException;
    Collection<Object> values() throws NetworkFailureException;
    Set<Map.Entry<Object, Object>> entrySet() throws NetworkFailureException;
}
