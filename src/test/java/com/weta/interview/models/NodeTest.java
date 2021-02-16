package com.weta.interview.models;

import com.weta.interview.constants.NodeType;
import com.weta.interview.exceptions.InvalidNodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class NodeTest {

    @Test
    public void TestRedisNode() throws InvalidNodeException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-redis-1";
        Integer port = 6379;
        Node node = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.REDIS)
                .build();

        Assertions.assertEquals(uuid, node.getNodeId());
        Assertions.assertEquals(hostname, node.getHostname());
        Assertions.assertEquals(port, node.getPort());
        Assertions.assertEquals(NodeType.REDIS, node.getType());
    }

    @Test
    public void TestMemcacheNode() throws InvalidNodeException {
        UUID uuid = UUID.randomUUID();
        String hostname = "weta-cache-memcache-1";
        Integer port = 11211;
        Node node = new Node.NodeBuilder()
                .nodeId(uuid)
                .hostname(hostname)
                .port(port)
                .type(NodeType.MEMCACHE)
                .build();

        Assertions.assertEquals(uuid, node.getNodeId());
        Assertions.assertEquals(hostname, node.getHostname());
        Assertions.assertEquals(port, node.getPort());
        Assertions.assertEquals(NodeType.MEMCACHE, node.getType());
    }

}
