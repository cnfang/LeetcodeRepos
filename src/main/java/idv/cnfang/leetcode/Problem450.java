package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Test;

import idv.cnfang.leetcode.Problem98.TreeNode;

import static org.assertj.core.api.Assertions.assertThat;

/**
Leetcode <Problem 450> Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).


Example:
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
*/

public class Problem450 {
	class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	     TreeNode() {}
	} 
	
	public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        else if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        
        //////////// root.val == key ///////////
        // case 1: no children at all
        if (root.left == null && root.right == null)
        	return null;
        
        // case 2: only right child
        else if (root.left == null && root.right != null)
            return root.right;
        
        // case 3: only left child
        else if (root.right == null && root.left != null)
            return root.left;
        
        // case 4: both children exist
        TreeNode returnNode = addNode(root.right, root.left);
        root.left = null;
        return returnNode;
    }
    
    private TreeNode addNode(TreeNode root, TreeNode child) {
        if (root == null)
            return child;
        if (child.val > root.val) {
            root.right = addNode(root.right, child);
            return root;
        }
        root.left = addNode(root.left, child);
        return root;
    }
    
    public static List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
    	dfs(root, ans);
    	return ans;
    }
    
    private static void dfs(TreeNode root, List<Integer> ans) {
    	if (root == null)
    		return;
    	dfs(root.left, ans);
    	ans.add(root.val);
    	dfs(root.right, ans);
    	return;
    }
    
    @Test
    public void test1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        
        List<Integer> expected = Arrays.asList(2,4,5,6,7);
        Problem450 sol = new Problem450();
        sol.deleteNode(root, 3);
        assertThat(inorderTraversal(root)).isEqualTo(expected);
    }
}