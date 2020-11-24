package idv.cnfang.leetcode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/** Leetcode <Problem 337> House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
  
====================== Solution =============
Try thinking from the rob(TreeNode root), this method always returns the maximum money robbing
from the house tree. How can we reuse this information to our next decision? 
Idea is to reveal all the decisions you make (robbing, notRobbing) and leave for your parent node to worry the rest decision

Two decisions parent will make:
1. If parent node decides to rob, the money it can get is current.rob = root.val + leftChild.notRob + rightChild.notRob;
2. If parent node decisions not to rob, the money it can get is current.notRob = Math.max(leftChild.rob, leftChild.notRob) + Math.max(rightChild.rob, rightChild.notRob);

*/
public class Problem337 {
	
	public class TreeNode {
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
	 
	private class Decision {
        int rob;			// how much money you get from robbing current node
        int notRob;			// how much money you get if not robbing current node
        Decision() {rob = 0; notRob = 0;}
    }
    
    public int rob(TreeNode root) {
        Decision decision = robbing(root);
        return Math.max(decision.rob, decision.notRob);
    }
    
    private Decision robbing(TreeNode root) {
        if (root == null)
            return new Decision();
        
        Decision current = new Decision();
        Decision leftChild = robbing(root.left);
        Decision rightChild = robbing(root.right);
        
        current.rob = root.val + leftChild.notRob + rightChild.notRob;
        current.notRob = Math.max(leftChild.rob, leftChild.notRob) + 
                         Math.max(rightChild.rob, rightChild.notRob);
        return current;
    }
    
    @Test
    public void test1() {
    	TreeNode root = new TreeNode(3);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(3);
    	
    	root.left.right = new TreeNode(3);
    	root.right.right = new TreeNode(1);
    	
    	Problem337 sol = new Problem337();
    	assertEquals(7, sol.rob(root));
    }
    
    @Test
    public void test2() {
    	TreeNode root = new TreeNode(3);
    	root.left = new TreeNode(4);
    	root.right = new TreeNode(5);
    	
    	root.left.left = new TreeNode(1);
    	root.left.right = new TreeNode(3);
    	
    	root.right.right = new TreeNode(1);
    	
    	Problem337 sol = new Problem337();
    	assertEquals(9, sol.rob(root));
    }
}