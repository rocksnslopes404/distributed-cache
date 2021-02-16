package com.weta.interview.hash;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;

public interface Hash {
    Integer hash(Object value) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException;
}
