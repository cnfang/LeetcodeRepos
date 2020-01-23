package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 148> Sort List

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5

*/

public class Problem148 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode sortList(ListNode head) {
        if (head == null) 
            return null;
        ListNode node = head;
        while (node.next != null)
            node = node.next;
        quickSort(head, null, head, node);
        return head;
    }
    
    private void quickSort(ListNode head, ListNode preStart, ListNode start, ListNode end) {
        if (start == null || end == null || start == end || end.next == start)
            return;

        int pivot = end.val;
        ListNode j = start, preJ = preStart;
        int tmp;
        for (ListNode node = start; node != end; node = node.next) {
            if (node.val < pivot) {
                tmp = node.val;
                node.val = j.val;
                j.val = tmp;
                
                preJ = j;
                j = j.next;
            }
        }

        end.val = j.val;
        j.val = pivot;
       
        quickSort(head, preStart, start, preJ);
        quickSort(head, j, j.next, end);
        return;
    }
    
    public static List<Integer> getList(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            ans.add(node.val);
            node = node.next;
        }
        return ans;
    }
    
    @Disabled @Test
    public void example1() {
        List<Integer> expected = Arrays.asList(1,2,3,4);
        ListNode head = new ListNode(4); 
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        
        Problem148 sol = new Problem148();
        sol.sortList(head);
        List<Integer> ans = Problem148.getList(head);
        assertThat(ans, equalTo(expected));
    }
    @Test
    public void example2() {
        List<Integer> expected = Arrays.asList(-1,0,3,4,5);
        ListNode head = new ListNode(-1); 
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        
        Problem148 sol = new Problem148();
        head = sol.sortList(head);
        List<Integer> ans = Problem148.getList(head);
        assertThat(ans, equalTo(expected));
    }
}
