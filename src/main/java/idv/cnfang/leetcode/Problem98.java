package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 98> Recursion II : Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

====================== Solution ======================
apply divide and conquer method, and set the min, max value to subproblem
- set min value as root.val if going to right sub-tree
- set max value as root.val if going to lefe sub-tree

*/


public class Problem98 {
	class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }

	}
    public boolean isValidBST(TreeNode root) {
        return innerValidBST(root, null, null);
    }
    
    private boolean innerValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) return false;
        
        if (root.left != null && root.val <= root.left.val) return false;
        
        if (root.right != null && root.val >= root.right.val) return false;
        
        return innerValidBST(root.left, min, root.val) & 
            innerValidBST(root.right, root.val, max);
    }
    
    
    @Test
    public void test_isValidBST_Case1() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
      
        Problem98 sol = new Problem98();
        assertTrue(sol.isValidBST(root));
    }
    
    @Test
    public void test_isValidBST_Case2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        
        Problem98 sol = new Problem98();
        assertFalse(sol.isValidBST(root));
    }
}