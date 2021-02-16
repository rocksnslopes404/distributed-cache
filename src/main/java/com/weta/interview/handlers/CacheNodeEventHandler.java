package com.weta.interview.handlers;

import com.weta.interview.models.Node;
import com.weta.interview.models.NodeManagerSingleton;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class CacheNodeEventHandler implements NodeEventHandler{

    @Override
    public void nodeAdded(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();
        mgr.nodes.add(node);

        // TODO:  distribute cache from near-by nodes
    }

    @Override
    public void nodeRemoved(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();
        mgr.nodes.remove(node);

        // Distribute the node's cache amongst other near by nodes before removing the node
        for (Map.Entry<Object, Object> entry : node.getCache().entrySet())  {
            Object key = entry.getKey();
            Object value = entry.getValue();
            Node toNode = mgr.nodes.getNode(key);
            toNode.getCache().put(key, value);
        }

    }

    @Override
    public void nodeShuttingDown(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();
        mgr.nodes.remove(node);
    }
}
