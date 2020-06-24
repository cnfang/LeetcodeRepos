package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 993> Cousins in Binary Tree
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

 

Example 1:
Input: root = [1,2,3,4], x = 4, y = 3
Output: false


Example 2:
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true


Example 3:
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
*/


public class Problem993 {
    
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
     
     class SolutionPair {
         TreeNode parent;
         int depth;
         public SolutionPair(TreeNode parent, int depth) {
             this.parent = parent;
             this.depth = depth;
         }
     }
     
     public boolean isCousins(TreeNode root, int x, int y) {
         if (root == null)
             return false;
         
         SolutionPair sx = dfs(root, 0, x, Integer.MAX_VALUE);
         SolutionPair sy = dfs(root, 0, y, sx.depth);
         
         return sx != null && sy != null && sx.depth == sy.depth && sx.parent != sy.parent;
     }
     
     private SolutionPair dfs(TreeNode root, int dep, int targetVal, int targetDepth) {
         // stop searching further
         if (root == null || dep > targetDepth)
             return null;
         
         // in case of root.val == targetVal
         if (root.val == targetVal)
             return new SolutionPair(null, dep-1);
         
         if (root.left != null && root.left.val == targetVal)
             return new SolutionPair(root, dep);
         
         if (root.right != null && root.right.val == targetVal)
             return new SolutionPair(root, dep);
         
         
         SolutionPair pair = dfs(root.left, dep+1, targetVal, targetDepth);
         return pair == null? dfs(root.right, dep+1, targetVal, targetDepth): pair;
     }
     
     // for junit testing
     private TreeNode root;
     private static Problem993 sol = new Problem993();
     
     @BeforeEach
     public void setUp() {
         root = new TreeNode();
     }
     
     @AfterEach
     public void tearDown() {
         root = null;
     }
     
     
     @Test
     public void test_example1() {
         root.val = 1;
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);
         root.left.left = new TreeNode(4);
         
         assertFalse(sol.isCousins(root, 4, 3));
     }
     
     @Test
     public void test_example2() {
         root.val = 1;
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);
         root.left.right = new TreeNode(4);
         root.right.right = new TreeNode(5);
         
         assertTrue(sol.isCousins(root, 5, 4));
     }
     
     @Test
     public void test_example3() {
         root.val = 1;
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);
         root.left.right = new TreeNode(4);
         
         assertFalse(sol.isCousins(root, 2, 3));
     }
     
}