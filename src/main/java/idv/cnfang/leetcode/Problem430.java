package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        public Node() {};
        public Node(int _val) {this.val = _val;}
        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
    
    public Node flatten(Node head) {
        Node dummy = new Node();
        visit(dummy, head);
        return dummy.next;
    }
    
    private Node visit(Node ancestor, Node root) {
        if (root == null)
            return ancestor;
        root.prev = ancestor;
        ancestor.next = root;
        ancestor.child = null;
        ancestor = ancestor.next;
        // tmp just in case of overriding
        Node tmp = ancestor.next;
        Node childTail = visit(ancestor, root.child);
        return visit(childTail, tmp);
    }
    
    public Node flatten2(Node head) {
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
    
    public List<Integer> traversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }
    
    private void dfs(List<Integer> ans, Node root) {
        if (root == null)
            return;
        ans.add(root.val);
        dfs(ans, root.child);
        dfs(ans, root.next);
        return;
    }
    
    @Test
    public void test1() {
        Problem430 sol = new Problem430();
        Node [] arr = {null, new Node(1), new Node(2), new Node(3)};
        Node head = arr[1];
        arr[1].next = arr[2]; arr[2].prev = arr[1];
        arr[1].child = arr[3]; arr[3].prev = arr[1];
        List<Integer> expected = Arrays.asList(1, 3, 2);
        
        Node root = sol.flatten(head);
        assertEquals(expected, traversal(root));
    }
    
    @Test
    public void test2() {
        Problem430 sol = new Problem430();
        Node [] arr = new Node[13];
        arr[0] = null;
        for (int i = 1; i < 13; i++)
            arr[i] = new Node(i);
        
        Node head = arr[1];
        arr[1].next = arr[2]; arr[2].prev = arr[1];
        arr[2].next = arr[3]; arr[3].prev = arr[2];
        arr[3].next = arr[4]; arr[4].prev = arr[3];
        arr[4].next = arr[5]; arr[5].prev = arr[4];
        arr[5].next = arr[6]; arr[6].prev = arr[5];
        
        arr[7].next = arr[8]; arr[8].prev = arr[7];
        arr[8].next = arr[9]; arr[9].prev = arr[8];
        arr[9].next = arr[10]; arr[10].prev = arr[9];
        
        arr[11].next = arr[12]; arr[12].prev = arr[11];
        
        arr[3].child = arr[7]; arr[7].prev = arr[3];
        arr[8].child = arr[11]; arr[11].prev = arr[8];
        
        List<Integer> expected = Arrays.asList(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6);
        
        Node root = sol.flatten(head);
        assertEquals(expected, traversal(root));
    }
}