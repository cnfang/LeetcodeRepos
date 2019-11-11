package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1022> Sum of Root To Leaf Binary Numbers

Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.


Example 1:
Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.

*/

public class Problem1022 {
    
    class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
    }
    
    public int sumRootToLeaf(TreeNode root) {
        int []totalSum = {0};
        int tmpSum = 0;
        
        dfs(root, totalSum, tmpSum);
        
        return totalSum[0];
    }
    
    private void dfs(TreeNode root, int []totalSum, int pretmpSum) {
        if (root == null) return;
        
        int currtmpSum = (pretmpSum << 1) + root.val;
        
        if (root.left == null && root.right == null) {
            totalSum[0] += currtmpSum;
            return;
        }
        
        dfs(root.left, totalSum, currtmpSum);
        dfs(root.right, totalSum, currtmpSum);
    }
    
    @Test 
    public void testsumRootToLeaf() {
        Problem1022 sol = new Problem1022();
        Problem1022.TreeNode root = sol.new TreeNode(1);
        
        root.left = sol.new TreeNode(0);
        root.right = sol.new TreeNode(1);
        
        root.left.left = sol.new TreeNode(0);
        root.left.right = sol.new TreeNode(1);
        
        root.right.left = sol.new TreeNode(0);
        root.right.right = sol.new TreeNode(1);
        
        int expected = 22;
        assertEquals(expected, sol.sumRootToLeaf(root));
    }
}


