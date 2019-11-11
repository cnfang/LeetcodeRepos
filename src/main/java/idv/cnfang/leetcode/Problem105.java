package idv.cnfang.leetcode;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 105> Binary Tree: Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
==========================================
1. root is at the first position of preorder array
2. locate the root location in inorder array, and separate the nodes in left/right tree 
3. recursive doing the same thing for left/right tree 

*/

public class Problem105 {
    
     class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
     
     public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) lookup.put(inorder[i], i);
        return buildTreeRange(lookup, inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
     }
    
    private TreeNode buildTreeRange(HashMap<Integer, Integer> lookup, int[] inorder, int startInorder, int endInorder, int[] preorder, int startPreorder, int endPreorder) {
        if (endInorder < startInorder) return null;
        
        int rootVal = preorder[startPreorder];
        TreeNode root = new TreeNode(rootVal);
        
        int i = lookup.get(rootVal);
        int numLeftNode = i - 1 - startInorder + 1;
        int numRightNode = (endInorder - startInorder + 1) - 1 - numLeftNode;
        
        root.left = buildTreeRange(lookup, inorder, startInorder, i-1, preorder, startPreorder+1, startPreorder+1+numLeftNode-1);
        root.right = buildTreeRange(lookup, inorder, i+1, endInorder, preorder, endPreorder-numRightNode+1, endPreorder);
        
        return root;
    }
    
    
    @Test
    public void testbuildTree() {
        int []inorder = {9,3,15,20,7};
        int []preorder = {3,9,20,15,7};
        
        Problem105 sol = new Problem105();
        TreeNode root = sol.buildTree(preorder, inorder);
    }
}


