package com.weta.interview.cache;

import com.weta.interview.constants.NodeType;
import com.weta.interview.models.AbstractFactory;

public class CacheFactory implements AbstractFactory<Cache> {

    @Override
    public Cache create(Enum nodeType) {
        switch ((NodeType)nodeType)  {
            case REDIS:
                return new RedisCache();
            case MEMCACHE:
                return new MemCache();
        }
        return null;
    }

}
