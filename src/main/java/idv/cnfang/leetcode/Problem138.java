package idv.cnfang.leetcode;
import java.util.*;

/**
Leetcode <Problem 138> Linked List : Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

*/


public class Problem138 {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
    
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        
        HashMap<Node, Node> dic = new HashMap<Node, Node>();
        Node curr = head;
        Node clone, cloneNext, cloneRandom;
        
        while( curr != null) {
            if (! dic.containsKey(curr)) {
                clone = new Node(curr.val, null, null);
                dic.put(curr, clone);
            } 
            clone = dic.get(curr);
            
            
            if (curr.next != null && !dic.containsKey(curr.next)) {
                cloneNext = new Node(curr.next.val, null, null);
                dic.put(curr.next, cloneNext);
            }
            cloneNext = dic.get(curr.next);
            

            if (curr.random != null && !dic.containsKey(curr.random)) {
                cloneRandom = new Node(curr.random.val, null, null);
                dic.put(curr.random, cloneRandom);
            }
            cloneRandom = dic.get(curr.random);
            
            // build connection
            clone.next = cloneNext;
            clone.random = cloneRandom;
            
            curr = curr.next;
        }
        
        return dic.get(head);
    }
}