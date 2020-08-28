package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
Leetcode <Problem 143> Reorder List

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.


Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

================== Solution =================
Solution 1: save all the nodes in a queue, reorder the linkedlist by alternatively removing last and first of the queue.
            Space O(number of nodes); Time O(number of nodes)
Solution 2: (a) get the middle the linkedlist and reverse the second half of the linkedlist 
            (b) sequentially rebuild the linkedlist by connecting nodes from first half and nodes from reversed half
            Space O(1); Time (number of nodes)
*/


public class Problem143 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
    
    public void reorderList(ListNode head) {
        if (head == null) 
            return;
        
        ArrayDeque<ListNode> q = new ArrayDeque<>();
        ListNode root = head.next;
        
        while (root != null) {
            q.add(root);
            root = root.next;
        }
        
        helper(q, head, true);
    }
    
    private void helper(ArrayDeque<ListNode> q, ListNode root, boolean fromLast) {
        if (q.isEmpty()) {
            root.next = null;
            return;
        }
        
        root.next = fromLast? q.removeLast(): q.remove();
        helper(q, root.next, !fromLast);
        return;
    }
   
    private List<ListNode> toList(ListNode root) {
        List<ListNode> list = new ArrayList<>();
        while (root != null) {
            list.add(root);
            root = root.next;
        }
        return list;
    }
    
    @Test
    public void test1() {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        List<ListNode> expected = Arrays.asList(root, root.next.next.next, root.next, root.next.next);
      
        Problem143 sol = new Problem143();
        sol.reorderList(root);
        List<ListNode> actual = toList(root);  
        assertThat(actual).isEqualTo(expected);
    }
    
}
