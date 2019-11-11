package idv.cnfang.leetcode;
import java.util.ArrayDeque;

/**
Leetcode <Problem 236> Binary Tree: Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 

Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
*/

public class Problem236 {
    
    class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        ArrayDeque<TreeNode> pathToP = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> pathToQ = new ArrayDeque<TreeNode>();
        
        findNode(pathToP, root, p);
        findNode(pathToQ, root, q);
        
        TreeNode currP = pathToP.pop();
        TreeNode currQ = pathToQ.pop();
        TreeNode ancestor = currP;
        while(currP == currQ && !pathToP.isEmpty() && !pathToQ.isEmpty()) {
            ancestor = currP;
            currP = pathToP.pop();
            currQ = pathToQ.pop();
        }
        
        if (currP == currQ) ancestor = currP;
        return ancestor;
    }
    
   private boolean findNode(ArrayDeque<TreeNode> pathToTarget, TreeNode root, TreeNode target) {
       if (root == null) return false;
       if (root == target) {
           pathToTarget.add(root);
           return true;
       }
       
       pathToTarget.add(root);
       if (findNode(pathToTarget, root.right, target)) return true;
       if (findNode(pathToTarget, root.left, target)) return true;
       pathToTarget.pollLast();
       return false;
   }
   
   public static void main(String []args) {
       Problem236 sol = new Problem236();
       
       TreeNode root = sol.new TreeNode(3);
       root.left = sol.new TreeNode(5);
       root.right = sol.new TreeNode(1);
       
       root.left.left = sol.new TreeNode(6);
       root.left.right = sol.new TreeNode(2);
       
       root.left.right.left = sol.new TreeNode(7);
       root.left.right.right = sol.new TreeNode(4);
       
       root.right.left = sol.new TreeNode(0);
       root.right.right = sol.new TreeNode(8);
       
       TreeNode ancestor = sol.lowestCommonAncestor(root, root.left, root.left.right.right);
   }
}