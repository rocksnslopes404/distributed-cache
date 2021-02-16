package com.weta.interview.models;

import com.weta.interview.hash.Hash;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<Node> {

    private final Hash hashFunction;
    private final int replicas;
    private final SortedMap<Integer, Node> circle = new TreeMap<Integer, Node>();

    public ConsistentHash(Hash hashFunction, int replicas, Collection<Node> nodes) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        this.hashFunction = hashFunction;
        this.replicas = replicas;
        for (Node node : nodes) {
            add(node);
        }
    }

    public void add(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        for (int i = 0; i < replicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    public void remove(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        for (int i = 0; i < replicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public Node getNode(Object key) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        if (circle.isEmpty())
            return null;
        int hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, Node> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}
