package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import idv.cnfang.leetcode.Problem98.TreeNode;

/**
Leetcode <Problem 1008> Construct Binary Search Tree from Preorder Traversal

Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

Example 1:
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]


Note: 
1 <= preorder.length <= 100
2. The values of preorder are distinct.
*/

public class Problem1008 {
	class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }

	}
	
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        
        return buildTree(preorder, new int[] {0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode buildTree(int []preorder, int []currIdx, int minV, int maxV) {
        if (currIdx[0] >= preorder.length)
            return null;
        
        if (preorder[currIdx[0]] < minV || preorder[currIdx[0]] > maxV)
            return null;
        
        TreeNode node = new TreeNode(preorder[currIdx[0]]);
        
        currIdx[0] += 1;
        
        node.left = buildTree(preorder, currIdx, minV, node.val);
        
        node.right = buildTree(preorder, currIdx, node.val, maxV);        
        
        return node;
    }
}


