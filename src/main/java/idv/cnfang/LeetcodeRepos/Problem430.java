package idv.cnfang.leetcode;
import java.util.*;

/**
Leetcode <Problem 430> Linked List : Flatten a Multilevel Doubly Linked List

You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.


Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL

======================================
1. DFS
2. Stack

*/
public class Problem430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}
        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
    
    public Node flatten(Node head) {
        if (head == null) return head;
        Node dummy = new Node();
        Node tail = dummy;
        Stack<Node> stack = new Stack<Node>();
        stack.add(head);
        
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            curr.prev = tail;
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) stack.add(curr.next);
            if (curr.child != null) stack.add(curr.child);
            curr.child = null;
        }
        head.prev = null;
        return dummy.next;
    }
}