package idv.cnfang.leetcode;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
Leetcode <Problem 404> Sum of Left Leaves

Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
=================== Solution =======================
1. BFS, use queue checking nodes layer by layer if current node is left child and leave node
2. DFS, helper function passed in a isLeft flag to indicate if current node is the left child

*/

public class Problem404 {
    
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode() {};
        TreeNode(int val) {this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root.left);
        deque.add(root.right);
        
        int acc = 0, qSize;
        boolean isLeft = true;
        TreeNode node;
        
        while (!deque.isEmpty()) {
            qSize = deque.size();
            for (int i = 0; i < qSize; i++, isLeft = !isLeft) {
                node = deque.poll();
                if (node == null)
                    continue;
                else if (node.left == null && node.right == null && isLeft)
                    acc += node.val;
                else {
                    deque.add(node.left);
                    deque.add(node.right);
                }
            }
        }
        return acc;
    }
    
    @Test 
    public void testbuildTree() {
        Problem404 sol = new Problem404();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        assertEquals(24, sol.sumOfLeftLeaves(root));
    }
}


