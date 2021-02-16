package com.weta.interview.models;

import com.weta.interview.constants.NodeType;

public interface AbstractFactory<T> {
    T create(Enum type) ;
}