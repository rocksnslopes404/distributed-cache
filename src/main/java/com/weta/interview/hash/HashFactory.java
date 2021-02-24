package com.weta.interview.hash;

import com.weta.interview.constants.HashType;
import com.weta.interview.models.AbstractFactory;

public class HashFactory implements AbstractFactory<Hash> {

    @Override
    public Hash create(Enum hashType) {

        switch ((HashType)hashType)  {
            case MD5:
                return new Md5Hash();
            case JAVA:
                return new JavaHash();
        }
        return null;
    }

}
