package idv.cnfang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
Leetcode <Problem 133> Queue & Stack : Clone Graph

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. 

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

Example:

Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.
*/

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

public class Problem133 {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> nodeMap = new HashMap<Node, Node>();
        
        // clone root node and add into nodeMap
        Node clone = new Node(node.val, new ArrayList<Node>());
        nodeMap.put(node, clone);
        
        traversal(nodeMap, node);
        return nodeMap.get(node);
    }
    
    /**
     * traversal whole graph, and put all the unseen nodes to the nodeMap, generate clone node 
     * and stop traveral when all nodes are seen.
     * @param nodeMap
     * @param root
     */
    private void traversal(HashMap<Node, Node> nodeMap, Node root) {
        // if nodeMap haven't see this node before, clone one, put them in the node book, and build connection between clone nodes
        for(Node next: root.neighbors) {
            if (nodeMap.containsKey(next)) {
                // build connection between clone nodes
                Node cloneNext = nodeMap.get(next);
                nodeMap.get(root).neighbors.add(cloneNext);
            }
            else {
                // clone one and put in the node book
                Node cloneNext = new Node(next.val, new ArrayList<Node>());
                nodeMap.put(next, cloneNext);
                
                // build connection between clone nodes
                nodeMap.get(root).neighbors.add(cloneNext);
                
                // traversal unseem node
                traversal(nodeMap, next);
            }
        }
        return;
    }
    
}