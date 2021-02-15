package com.weta.interview.models;

import com.weta.interview.constants.NodeType;

import java.util.UUID;

public class Node {
    private UUID nodeId;
    private String hostname;
    private int port;
    private NodeType type;
}

