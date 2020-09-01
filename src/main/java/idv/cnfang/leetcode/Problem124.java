package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import idv.cnfang.leetcode.Problem98.TreeNode;

/**
Leetcode <Problem 124> Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.


Example 1:
Input: [1,2,3]

       1
      / \
     2   3

Output: 6


Example 2:
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

==========================================
*/

public class Problem124 {
	class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }

	}
	
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        int [] maxS = new int [] {root.val};
        getMaxSubtree(maxS, root);
        
        return maxS[0];
    }
    
    private int getMaxSubtree(int []maxS, TreeNode root) {
        if (root == null)
            return 0;
        
        int left = getMaxSubtree(maxS, root.left);
        int right = getMaxSubtree(maxS, root.right);
        
        // maximum path summation could be one of four scenario
        // 1. current node has maximum value
        int currNode = root.val;
        // 2. summation of current node and left tree 
        int currNodeWithLTree = left + root.val;
        // 3. summation of current node and right tree
        int currNodeWithRTree = right + root.val;
        // 4. summation of current node, left and right tree
        int currNodeWithLRTree = left + right + root.val;
        
        // only return value with local maximum 
        int returnToParentNode = Math.max(currNode, 
                                          Math.max(currNodeWithLTree, currNodeWithRTree));
        
        if (currNodeWithLRTree > maxS[0])
            maxS[0] = currNodeWithLRTree;
        
        if (returnToParentNode > maxS[0])
            maxS[0] = returnToParentNode;
        
        return returnToParentNode;
    }
    
}