package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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
        ListNode() {}
        ListNode(int x) { val = x; }
    }
//    public ListNode sortList(ListNode head) {
//        if (head == null) 
//            return null;
//        ListNode node = head;
//        while (node.next != null)
//            node = node.next;
//        quickSort(head, null, head, node);
//        return head;
//    }
//    
//    private void quickSort(ListNode head, ListNode preStart, ListNode start, ListNode end) {
//        if (start == null || end == null || start == end || end.next == start)
//            return;
//
//        int pivot = end.val;
//        ListNode j = start, preJ = preStart;
//        int tmp;
//        for (ListNode node = start; node != end; node = node.next) {
//            if (node.val < pivot) {
//                tmp = node.val;
//                node.val = j.val;
//                j.val = tmp;
//                
//                preJ = j;
//                j = j.next;
//            }
//        }
//
//        end.val = j.val;
//        j.val = pivot;
//       
//        quickSort(head, preStart, start, preJ);
//        quickSort(head, j, j.next, end);
//        return;
//    }
    public ListNode sortList(ListNode head) {
    	//return method2(head);
    	return method1(head);
    }
    
    private ListNode method1(ListNode head) {
    	if (head == null) return null;
    	List<ListNode> myList = new ArrayList<>();
    	myList.add(head);
    	
    	while (head.next != null) {
    		myList.add(head.next);
    		head = head.next;
    	}
    	Collections.sort(myList, (n1, n2) -> Integer.compare(n1.val, n2.val));
    	ListNode dummy = new ListNode();
    	ListNode pointer = dummy;
    	for (ListNode node: myList) {
    		pointer.next = node;
    		pointer = pointer.next;
    	}
    	pointer.next = null;
    	return dummy.next;
    }
    
    private ListNode method2(ListNode head) {
    	ListNode []ans = sortListReturnWithTail(head);
        return ans == null? null: ans[0];
    }
    
    // return {head, tail} of the list
    private ListNode[] sortListReturnWithTail(ListNode head) {
    	if (head == null)
    		return null;
    	if (head.next == null)
    		return new ListNode[] {head, head};
    	
        ListNode []subLists = breakInto2List(head);
        ListNode[] sHalf = sortListReturnWithTail(subLists[0]);
        ListNode[] lHalf = sortListReturnWithTail(subLists[1]);
        
        if (lHalf == null) {
        	 sHalf[1].next = head;
        	 return new ListNode[] {sHalf[0], head};
        } else if (sHalf == null) {
        	head.next = lHalf[0];
        	return new ListNode[] {head, lHalf[1]};
        }
        
        sHalf[1].next = head;
        head.next = lHalf[0];
        return new ListNode[] {sHalf[0], lHalf[1]};
    }
    
    // return {head of list where all elements are smaller or equal to head.val,
    //		   head of list where all elements are larger than head.val}
    private ListNode[] breakInto2List(ListNode head) {
        ListNode smallDummy = new ListNode();
        ListNode largeDummy = new ListNode();
        ListNode htmp = head, stmp = smallDummy, ltmp = largeDummy;
        
        while (htmp.next != null) {
            if (htmp.next.val <= head.val) {
                stmp.next = htmp.next;
                stmp = stmp.next;
            } else {
                ltmp.next = htmp.next;
                ltmp = ltmp.next;
            }
            htmp = htmp.next;
        }
        stmp.next = null;
        ltmp.next = null;
        head.next = null;
        return new ListNode[]{smallDummy.next, largeDummy.next};
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
    
    @Test
    public void example1() {
        List<Integer> expected = Arrays.asList(1,2,3,4);
        ListNode head = new ListNode(4); 
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        
        Problem148 sol = new Problem148();
        head = sol.sortList(head);
        List<Integer> ans = Problem148.getList(head);
        assertThat(ans, equalTo(expected));
    }
    
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
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
    
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void example3() {
        List<Integer> expected = Arrays.asList(1,2,3,4);
        ListNode head = new ListNode(4); 
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        
        Problem148 sol = new Problem148();
        head = sol.sortList(head);
        List<Integer> ans = Problem148.getList(head);
        assertThat(ans, equalTo(expected));
    }
}
