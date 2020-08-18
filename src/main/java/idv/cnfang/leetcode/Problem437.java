package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 437> Path Sum III

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

======================================

*/
public class Problem437 {
  
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
    public int pathSum(TreeNode root, int sum) {
        // sum collection to frequency
        Map<Integer, Integer> prefix = new HashMap<>();
        int []paths = {0};
        prefix.put(0, 1);
        dfs(prefix, paths, root, sum, 0);
        return paths[0];
    }
    
    private void dfs(Map<Integer, Integer> prefix, int []paths, TreeNode root,
                     int sum, int preSum) {
        if (root == null)
            return;
        
        int currSum = preSum + root.val;
        
        
        // check if sum exists between prefix nodes to current node
        paths[0] = paths[0] + prefix.getOrDefault(currSum - sum, 0);
        
        prefix.compute(currSum, (k, v) -> v == null? 1: v+1);
        
       
        dfs(prefix, paths, root.left, sum, currSum);
        dfs(prefix, paths, root.right, sum, currSum);
        
        // undo everything has done on current node before return to root node
        prefix.compute(currSum, (k, v) -> v-1);
        return;
    }
    
    @Test
    public void test1() {
        Problem437 sol = new Problem437();
        int sum = 8;
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        
        
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        assertEquals(3, sol.pathSum(root, sum));
    }
}