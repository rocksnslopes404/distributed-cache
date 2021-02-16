package com.weta.interview.handlers;

import com.weta.interview.exceptions.NetworkFailureException;
import com.weta.interview.models.Node;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;

public interface NodeEventHandler {
    /*
     * Difference between nodeRemoved and nodeShuttingDown is that nodeRemoved means the
     * node is down already, while nodeShuttingDown signals the intent to shutdown.
     * nodeShuttingDown is only required in the challenge portion
     */
    void nodeAdded(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, NetworkFailureException;
    void nodeRemoved(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException, NetworkFailureException;
    void nodeShuttingDown(Node node) throws UnsupportedEncodingException, NoSuchAlgorithmException, DigestException;
}

