package com.weta.interview.hash;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash implements Hash  {
    @Override
    public Integer hash(Object value) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        byte[] bytesOfMessage = value.toString().getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(bytesOfMessage, 0, bytesOfMessage.length);
    }
}
