package com.weta.interview.models;

import com.weta.interview.hash.Hash;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    public Set<Node> add(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {

        Set<Node> adjacentNodes = new HashSet<Node>();
        for (int i = 0; i < replicas; i++) {
            int circleKey = Math.abs(hashFunction.hash(node.toString() + i));
            circle.put(circleKey, node);

            // Gather the adjacent nodes
            SortedMap<Integer, Node> headMap = circle.headMap(circleKey);
            SortedMap<Integer, Node> tailMap = circle.tailMap(circleKey);
            Node headNode = headMap.isEmpty() ? circle.get(circle.firstKey()) : headMap.get(headMap.firstKey());
            Node tailNode = tailMap.isEmpty() ? circle.get(circle.firstKey()) : tailMap.get(tailMap.firstKey());
            if (!node.equals(headNode))
                adjacentNodes.add(headNode);
            if (!node.equals(tailNode))
                adjacentNodes.add(tailNode);
        }
        return adjacentNodes;
    }

    public void remove(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        for (int i = 0; i < replicas; i++) {
            int circleKey = Math.abs(hashFunction.hash(node.toString() + i));
            circle.remove(circleKey);
        }
    }

    public Node getNode(Object key) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        if (circle.isEmpty())
            return null;
        int circleKey = Math.abs(hashFunction.hash(key));
        if (!circle.containsKey(circleKey)) {
            SortedMap<Integer, Node> tailMap = circle.tailMap(circleKey);
            circleKey = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(circleKey);
    }

}
