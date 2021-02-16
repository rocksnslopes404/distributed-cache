package com.weta.interview.handlers;

import com.weta.interview.constants.NodeType;
import com.weta.interview.exceptions.InvalidNodeException;
import com.weta.interview.exceptions.NetworkFailureException;
import com.weta.interview.models.Node;
import com.weta.interview.models.NodeManagerSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CacheNodeEventHandlerTest {

    List<Node> nodes = new ArrayList<Node>();
    NodeManagerSingleton nodeMgr;
    private static final int ITERATIONS = 100;
    CacheNodeEventHandler handler = new CacheNodeEventHandler();

    @Test
    public void SimpleCacheDistributionTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, InvalidNodeException, NetworkFailureException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build());

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-1";
        port = 11211;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build());

        nodeMgr = NodeManagerSingleton.reset();
        for (Node node: nodes)
            nodeMgr.nodes.add(node);

        for (int i=0; i<ITERATIONS; i++)  {
            String key = UUID.randomUUID().toString();
            String value = UUID.randomUUID().toString();
            nodeMgr.nodes.getNode(key).getCache().put(key, value);
        }

        int totalEntries = 0;
        for (Node node : nodes) {
            totalEntries = totalEntries + node.getCache().entrySet().size();
            Assertions.assertNotEquals(0, node.getCache().entrySet().size());
        }

        Assertions.assertEquals(ITERATIONS, totalEntries);
    }

    @Test
    public void HandlerAddTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, InvalidNodeException, NetworkFailureException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build());

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-1";
        port = 11211;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build());

        nodeMgr = NodeManagerSingleton.reset();
        for (Node node: nodes)
            nodeMgr.nodes.add(node);

        // Build the cache
        for (int i=0; i<ITERATIONS; i++)  {
            String key = UUID.randomUUID().toString();
            String value = UUID.randomUUID().toString();
            nodeMgr.nodes.getNode(key).getCache().put(key, value);
        }

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-2";
        port = 11211;
        Node newNode = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build();

        nodes.add(newNode);
        handler.nodeAdded(newNode);

        int totalEntries = 0;
        for (Node node : nodes) {
            totalEntries = totalEntries + node.getCache().entrySet().size();
            Assertions.assertNotEquals(0, node.getCache().entrySet().size());
        }
        Assertions.assertEquals(ITERATIONS, totalEntries);
    }

    @Test
    public void HandlerRemoveTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, InvalidNodeException, NetworkFailureException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build());

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-1";
        port = 11211;
        nodes.add(new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build());

        nodeMgr = NodeManagerSingleton.reset();
        for (Node node: nodes)
            nodeMgr.nodes.add(node);

        // Build the cache
        for (int i=0; i<ITERATIONS; i++)  {
            String key = UUID.randomUUID().toString();
            String value = UUID.randomUUID().toString();
            nodeMgr.nodes.getNode(key).getCache().put(key, value);
        }

        uuid = UUID.randomUUID();
        hostname = "weta-cache-memcache-2";
        port = 11211;
        Node newNode = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build();

        nodes.add(newNode);
        handler.nodeAdded(newNode);
        handler.nodeRemoved(newNode);

        boolean removedNodeEmpty = false;
        for (Node node : nodes)  {
            if (node.getCache().entrySet().size() == 0)
                removedNodeEmpty = true;
        }
        Assertions.assertTrue(removedNodeEmpty);
    }

}
