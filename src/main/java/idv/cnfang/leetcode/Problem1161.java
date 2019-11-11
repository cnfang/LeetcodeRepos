package idv.cnfang.leetcode;

import java.util.*;

/**
Leetcode <Problem 1161> Maximum Level Sum of a Binary Tree

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:



Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
*/

public class Problem1161 {
    class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
    public int maxLevelSum(TreeNode root) {
        
        if (root == null) return 1;
        ArrayList<Integer> record = new ArrayList<Integer>();
        dfs(root, 1, record);
        
        int max = Integer.MIN_VALUE;
        int maxLevel = 1;
        for (int i = 0; i < record.size(); i++) {
            if (record.get(i) > max) {
                max = record.get(i);
                maxLevel = i+1;
            }
        }
        
        return maxLevel;
       
    }
    
    private void dfs(TreeNode root, int level, ArrayList<Integer> record) {
        if (root == null) return;
        if (record.size() < level) record.add(0);
        record.set(level-1, record.get(level-1)+root.val);
        
        dfs(root.left, level+1, record);
        dfs(root.right, level+1, record);
        return;
    }
    
}