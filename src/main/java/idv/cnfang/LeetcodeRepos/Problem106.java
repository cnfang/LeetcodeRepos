package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 106> Binary Tree: Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
==========================================
1. root is at the final position of postorder array
2. locate the root location in inorder array, and separate the nodes in left/right tree 
3. recursive doing the same thing for left/right tree 
*/

public class Problem106 {
    
     class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
     
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeRange(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    private TreeNode buildTreeRange(int[] inorder, int startInorder, int endInorder, int[] postorder, int startPostorder, int endPostorder) {
        if (endInorder < startInorder) return null;
        
        int rootVal = postorder[endPostorder];
        TreeNode root = new TreeNode(rootVal);
        
        int i = startInorder;
        while (inorder[i] != rootVal) i++;
        int numLeftNode = i - 1 - startInorder + 1;
        int numRightNode = (endInorder - startInorder + 1) - 1 - numLeftNode;
        
        root.left = buildTreeRange(inorder, startInorder, i-1, postorder, startPostorder, startPostorder+numLeftNode-1);
        root.right = buildTreeRange(inorder, i+1, endInorder, postorder, endPostorder-1-numRightNode+1, endPostorder-1);
        
        return root;
    }
    
    @Test 
    public void testbuildTree() {
        int []inorder = {9,3,15,20,7};
        int []postorder = {9,15,7,20,3};
        
        Problem106 sol = new Problem106();
        TreeNode root = sol.buildTree(inorder, postorder);
    }
}


