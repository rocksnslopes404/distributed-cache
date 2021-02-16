package com.weta.interview.models;

import com.weta.interview.constants.Global;
import com.weta.interview.hash.Md5Hash;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

public class NodeManagerSingleton {

    private static NodeManagerSingleton instance;
    public ConsistentHash<Node> nodes;

    private NodeManagerSingleton() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        nodes = new ConsistentHash(new Md5Hash(), Global.DEFAULT_NUMBER_OF_REPLICAS, Collections.emptyList());
    }

    public static synchronized NodeManagerSingleton getInstance() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        if (instance == null) {
            instance = new NodeManagerSingleton();
        }
        return instance;
    }

    public static synchronized NodeManagerSingleton reset() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        instance = new NodeManagerSingleton();
        return instance;
    }
}
