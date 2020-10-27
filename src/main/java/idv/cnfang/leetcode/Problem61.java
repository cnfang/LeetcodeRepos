package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Leetcode <Problem 61> Rotate List

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
================ Solution

*/

public class Problem61 {
	
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	 
	public ListNode rotateRight(ListNode head, int k) {
        if (k == 0) return head;
        if (head == null) return head;
        
        ListNode first = head, second = head;
        for (int i = 0; i < k; i++) {
            first = first.next;
            if (first == null) {
                k = k % (i+1);
                i = -1;
                first = head;
            }
        }
        
        if (second == first) 
            return head;
        
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        
        ListNode newHead = second.next;
        second.next = null;
        first.next = head;
        return newHead;
	}
     
	public static List<Integer> traversal(ListNode head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.val);
			head = head.next;
		}
		return ans;
	}
	
    @Test
    public void test1() {
        Problem61 sol = new Problem61();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        assertEquals(Arrays.asList(4,5,1,2,3), traversal(sol.rotateRight(head, 2)));
    }
    
    @Test
    public void test2() {
        Problem61 sol = new Problem61();
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        
        assertEquals(Arrays.asList(2,0,1), traversal(sol.rotateRight(head, 4)));
    }
     
}
