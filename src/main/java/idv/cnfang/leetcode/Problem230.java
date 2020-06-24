package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 230> Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1


Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
*/

public class Problem230 {
    
     // Definition for a binary tree node.
     class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
     
    public int kthSmallest(TreeNode root, int k) {
        // ref[0] is the number of nodes given root node (including),
        // ref[1] is the kth smallest number
        int []ref = {0, -1};
        dfs(root, ref, k);
        return ref[1];
    }
    
    private void dfs(TreeNode root, int []ref, int k) {
        if (root == null)
            return;
        
        dfs(root.left, ref, k);
        
        ref[0] += 1;
        if (ref[0] == k) {
            ref[1] = root.val;
            return;
        }
        
        dfs(root.right, ref, k);
        return;
    }
    
    
    @Test
    public void test_example1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        
        int k = 1;
        int expected = 1;
        Problem230 sol = new Problem230();
        assertEquals(expected, sol.kthSmallest(root, k));
    }
    
    @Test
    public void test_example2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        
        int k = 3;
        int expected = 3;
        Problem230 sol = new Problem230();
        assertEquals(expected, sol.kthSmallest(root, k));
    }
    
}