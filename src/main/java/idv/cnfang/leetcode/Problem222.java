package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 222> Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

=========== Solution =============
1. apply recursive way to accumulate node number
*/

public class Problem222 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    public int countNodes(TreeNode root) {
        return traverse(root, 0);
    }
    
    private int traverse(TreeNode root, int nodeNum) {
        if (root == null)
            return nodeNum;
        
        int l = traverse(root.left, nodeNum);
        int r = traverse(root.right, l);
        return r + 1;
    }
    
    @Test
    public void test_example3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        
        int expected = 6;
        Problem222 sol = new Problem222();
        assertEquals(expected, sol.countNodes(root));
    }
    
}