package com.weta.interview.handlers;

import com.weta.interview.exceptions.NetworkFailureException;
import com.weta.interview.models.Node;
import com.weta.interview.models.NodeManagerSingleton;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CacheNodeEventHandler implements NodeEventHandler{

    @Override
    public void nodeAdded(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, NetworkFailureException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();

        // Add the new node and re-distribute cache entries from adjacent nodes
        Set<Node> adjacentNodes = mgr.nodes.add(node);
        for (Node adjacentNode : adjacentNodes)  {
            Iterator it = adjacentNode.getCache().entrySet().iterator();
            while (it.hasNext())  {
                Map.Entry pair = (Map.Entry)it.next();
                Object key = pair.getKey();
                Object value = pair.getValue();

                // Needs to be moved to newly added node, transfer and remove from old
                if (mgr.nodes.getNode(key).equals(node))  {
                    node.getCache().put(key, value);
                    it.remove();
                }
            }
        }
    }

    @Override
    public void nodeRemoved(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, NetworkFailureException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();
        mgr.nodes.remove(node);

        // Distribute the node's cache amongst other near by nodes before removing the node
        for (Map.Entry<Object, Object> entry : node.getCache().entrySet())  {
            Object key = entry.getKey();
            Object value = entry.getValue();
            Node targetNode = mgr.nodes.getNode(key);
            targetNode.getCache().put(key, value);
        }
        node.getCache().removeAll();
    }

    @Override
    public void nodeShuttingDown(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException {
        NodeManagerSingleton mgr = NodeManagerSingleton.getInstance();
        mgr.nodes.remove(node);
    }
}
