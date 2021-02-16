package com.weta.interview.models;

import com.weta.interview.constants.Global;
import com.weta.interview.constants.NodeType;
import com.weta.interview.exceptions.InvalidNodeException;
import com.weta.interview.hash.Md5Hash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class ConsistentHashTest {

    private static final int ITERATIONS = 25;

    @Test
    public void Md5DistributionTest() throws InvalidNodeException, UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        Node redisNode1 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        uuid = UUID.randomUUID();
        hostname = "weta-cache-redis-2";
        port = 6379;
        Node redisNode2 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-1";
        port = 11211;
        Node memcacheNode1 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build();

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-2";
        port = 11211;
        Node memcacheNode2 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build();

        ConsistentHash<Node> nodes = new ConsistentHash(new Md5Hash(),
                Global.DEFAULT_NUMBER_OF_REPLICAS, Arrays.asList(
                        redisNode1, redisNode2, memcacheNode1, memcacheNode2));

        int count = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Node node1 = nodes.getNode(UUID.randomUUID().toString());
            Node node2 = nodes.getNode(UUID.randomUUID().toString());
            if (!node1.equals(node2))
                count++;
        }

        Assertions.assertNotEquals(0, count);

    }

    @Test
    public void Md5AddTest() throws InvalidNodeException, UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        Node redisNode1 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        uuid = UUID.randomUUID();
        hostname = "weta-cache-redis-2";
        port = 6379;
        Node redisNode2 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        ConsistentHash<Node> nodes = new ConsistentHash(new Md5Hash(),
                Global.DEFAULT_NUMBER_OF_REPLICAS, Arrays.asList(
                redisNode1));
        nodes.add(redisNode2);

        int count = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Node node1 = nodes.getNode(UUID.randomUUID().toString());
            Node node2 = nodes.getNode(UUID.randomUUID().toString());
            if (!node1.equals(node2))
                count++;
        }
        Assertions.assertNotEquals(0, count);
    }

    @Test
    public void Md5RemoveTest() throws InvalidNodeException, UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        Node redisNode1 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        uuid = UUID.randomUUID();
        hostname = "weta-cache-redis-2";
        port = 6379;
        Node redisNode2 = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        ConsistentHash<Node> nodes = new ConsistentHash(new Md5Hash(),
                Global.DEFAULT_NUMBER_OF_REPLICAS, Arrays.asList(
                redisNode1, redisNode2));
        nodes.remove(redisNode2);

        int count = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Node node1 = nodes.getNode(UUID.randomUUID().toString());
            Node node2 = nodes.getNode(UUID.randomUUID().toString());
            if (!node1.equals(node2))
                count++;
        }
        Assertions.assertEquals(0, count);
    }

}
