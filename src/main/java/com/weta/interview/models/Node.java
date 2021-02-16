package com.weta.interview.models;

import com.weta.interview.models.cache.Cache;
import com.weta.interview.models.cache.CacheFactory;
import com.weta.interview.constants.NodeType;
import com.weta.interview.exceptions.InvalidNodeException;

import java.util.Objects;
import java.util.UUID;

public class Node {
    private UUID nodeId;
    private String hostname;
    private int port;
    private NodeType type;
    private Cache cache;

    private Node(NodeBuilder builder) {
        this.nodeId = builder.nodeId;
        this.hostname = builder.hostname;
        this.port = builder.port;
        this.type = builder.type;
        this.cache = builder.cache;
    }

    public UUID getNodeId() {
        return nodeId;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public NodeType getType() {
        return type;
    }

    public Cache getCache()  {
        return cache;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return port == node.port && Objects.equals(nodeId, node.nodeId) && Objects.equals(hostname, node.hostname) && type == node.type;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", type=" + type +
                '}';
    }

    public static class NodeBuilder
    {
        private UUID nodeId;
        private String hostname;
        private int port;
        private NodeType type;
        private Cache cache;

        public NodeBuilder() {
        }
        public NodeBuilder nodeId(UUID nodeId) {
            this.nodeId = nodeId;
            return this;
        }
        public NodeBuilder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }
        public NodeBuilder port(int port) {
            this.port = port;
            return this;
        }
        public NodeBuilder type(NodeType type) {
            this.type = type;
            this.cache = new CacheFactory().create(type);
            return this;
        }

        public Node build() throws InvalidNodeException {
            Node node =  new Node(this);
            if (validateNode(node))
                return node;
            throw new InvalidNodeException("Invalid node parameters");
        }

        private boolean validateNode(Node node) {
            // TODO: Validate the node parameters
            return true;
        }
    }
}

