package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 226> Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9


Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1


Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.

*/

public class Problem226 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {};
        TreeNode(int val) {this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right) {this.val = val; this.left = left; this.right = right;}
    }
    
    public TreeNode invertTree(TreeNode root) {
        swap(root);
        return root;
    }
    
    private void swap(TreeNode root) {
        if (root == null)
            return;
        
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        
        swap(root.left);
        swap(root.right);
        return;
    }
    
}