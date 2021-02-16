##### **Java Distributed Cache Challenge**

This challenge is split into 3 parts, each building on the previous. 
An introduction to consistent hashing can be found here: http://michaelnielsen.org/blog/consistent hashing/ 
All associated code can be found at the end of this document. You can adapt/extend it as necessary. There is no trap in this challenge, don't feel you have to implement all of the parts. Please keep in mind the following: 

• The goal is to assess your ability to write clean, testable object oriented code, while  implementing a known algorithm 

• Use Java 8 and organize your code as you would for any other classic Java project • Software development best practices are welcome, at your discretion 

• This code will potentially be used as a discussion support around a coffee/beer/whatever • Develop within a repository like Github and send us the link when you are done

###### **Basic**

Implement a distributed caching mechanism using a consistent hashing algorithm. 
It is required that a client can cache, retrieve and invalidate keys from the distributed cache. It is also  required that different caching backends can be used. You should not implement the backend  connectors, but you can implement a dummy plain old Java object backend for example. 
To complete the basic task, it is safe to assume that: 

• All backend caches are an instance of the same application. ie.: they are all memcached or redis etc 

• The caching backend will be known at compile time 

• The cache size is static, and there are no failures at all. 

• Consistent hashing replicas are not required (ie.: each node has only one point on the unit  circle)

###### **Extension**

This part extends on the work done in the basic portion. The aim is to make the application more  dynamic. 
For this part, you can assume that any time a node is added or removed from the cluster, the host  application will call an instance of the NodeEventHandler interface (which you should implement). 
You should also add consistent hashing replicas, so that each node has multiple points on the unit  circle. 
To complete the extended task, it is safe to assume that:

• All backend caches are an instance of the same application. ie.: they are all memcached or  redis etc 

• The caching backend will be known at compile time 

• You do not need to worry about transferring key/values between nodes when a node is  added or removed.

• Your NodeEventHandler instance WILL be called every time a node is added or removed (i.e.  it is 100% reliable) 

• The number of replicas will be known at compile time 

• There are no failures at all (all additions and removals are done intentionally)

###### **Challenge**

The aim of this part is to further increase how dynamic the caching infrastructure is and to optimise  the performance. 
The back end caches are no longer homogeneous. You will need to be able to use the NodeType  enum value on the node to instantiate various backend connectors. There is no need to implement  the connectors. Failures occur. This includes hardware, software and network failures. Your code can  no longer assume that NodeEventHandler is called every time a node has failed. It is still safe to  assume that NodeEventHandler will be called every time a node is added. When a new node is  added, you should transfer to it the keys it should be handling that already exist in other nodes, and  invalidate those keys in the other nodes. When anode is shutting down, you should transfer from it  all to the nodes that will be handling them once the node is removed

###### **Associated code**

```
public interface NodeEventHandler {
    /*
     * Difference between nodeRemoved and nodeShuttingDown is that nodeRemoved means the  * node is down already, while nodeShuttingDown signals the intent to shutdown.  * nodeShuttingDown is only required in the challenge portion
     */
    void nodeAdded(Node node);
    void nodeRemoved(Node node);
    void nodeShuttingDown(Node node);
}

public class Node {
    private UUID nodeId;
    private String hostname;
    private int port;
    private NodeType type
}

public enum NodeType {
    // Example: REDIS, MEMCACHE, ...
}
```

