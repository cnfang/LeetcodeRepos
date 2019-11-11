package idv.cnfang.leetcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
Leetcode <Problem 652> Hash Table: Find Duplicate Subtrees

Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
   /   /
  5   4
     /
    5
    
1,2,3,4,null,2,4,5,null,4,null,null,null,null,null,5,null
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
*/

public class Problem652 {
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; } 
     }
     
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
       
        Set<TreeNode> sol = new HashSet<TreeNode>();
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        HashMap<String, TreeNode> dic = new HashMap<String, TreeNode>();
        postOrderTraversal(dic, count, sol, root);
       
        return new ArrayList<TreeNode>(sol);
    }
    
    private String postOrderTraversal(HashMap<String, TreeNode> dic, HashMap<String, Integer> count, Set<TreeNode> sol, TreeNode root) {
        if (root == null) return "#";
        String key = String.valueOf(root.val) + "," + postOrderTraversal(dic, count, sol, root.left) + postOrderTraversal(dic, count, sol, root.right);
        if (count.containsKey(key)) {
            count.replace(key, count.get(key)+1);
            sol.add(dic.get(key));
        }
        else {
            count.put(key, 1);
            dic.put(key, root);
        }
        return key;
    }
    
    public static void main(String []args) {
        Problem652 sol = new Problem652();
        TreeNode root = sol.new TreeNode(0);
        root.left = sol.new TreeNode(0);
        root.right = sol.new TreeNode(0);
        
        root.left.left = sol.new TreeNode(0);
        root.right.right = sol.new TreeNode(0);
        
        root.left.left.left = sol.new TreeNode(0);
        root.left.left.right = sol.new TreeNode(0);
        root.right.right.left = sol.new TreeNode(0);
        root.right.right.right = sol.new TreeNode(0);
        
        List<TreeNode> ans = sol.findDuplicateSubtrees(root);
        System.out.println(ans);
       
    }
}