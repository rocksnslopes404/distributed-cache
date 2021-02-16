package com.weta.interview.hash;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaHash implements Hash  {
    @Override
    public Integer hash(Object value) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        return value.hashCode();
    }
}
