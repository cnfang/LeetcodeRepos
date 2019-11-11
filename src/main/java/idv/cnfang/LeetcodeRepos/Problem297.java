package idv.cnfang.leetcode;
/**
Leetcode <Problem 297> Binary Tree: Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

// preorder using recursive
public class Problem297 {
    class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        StringBuilder out = new StringBuilder();
        preorder(out, root);
        out.deleteCharAt(out.length()-1);
        return out.toString();
    }
    
    private void preorder(StringBuilder out, TreeNode curr) {
        if (curr == null) {
            out.append("#" + ",");
            return;
        }
        out.append(curr.val + ",");
        preorder(out, curr.left);
        preorder(out, curr.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "" || data == "#") return null;
        String []val = data.split(",");
        TreeNode root = reconstruct(val, new int[] {0});
        return root;
    }
    
    private TreeNode reconstruct(String []val, int []idx) {
        if (idx[0] == val.length) return null;
        
        TreeNode root;
        if ("#".equals(val[idx[0]])) {
            root = null;
            idx[0]++;
            return root;
        }
        
        root = new TreeNode(Integer.valueOf(val[idx[0]]).intValue());
        idx[0]++;
        root.left = reconstruct(val, idx);
        root.right = reconstruct(val, idx);
        return root;
    }
    
    public static void main(String []args) {
        Problem297 sol = new Problem297();
        
        TreeNode root = sol.new TreeNode(1);
        root.left = sol.new TreeNode(2);
        root.right = sol.new TreeNode(3);
        
        root.right.left = sol.new TreeNode(4);
        root.right.right = sol.new TreeNode(5);
        
        String out = sol.serialize(root);
        System.out.println(out);
        
        TreeNode clone = sol.deserialize(out);
        System.out.println(sol.serialize(clone));
    }
}
// Level order, slow
//public class Problem297 {
//    class TreeNode {
//             int val;
//             TreeNode left;
//             TreeNode right;
//             TreeNode(int x) { val = x; }
//    }
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) return "#";
//        StringBuilder out = new StringBuilder();
//        
//        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        
//        while (!queue.isEmpty()) {
//            int n = queue.size();
//            for (int i = 0; i < n; i++) {
//                TreeNode curr = queue.removeFirst();
//                if (curr == null) {
//                    out.append("#" + ",");
//                    continue;
//                }
//                out.append(curr.val + ",");
//                queue.add(curr.left);
//                queue.add(curr.right);
//            }
//        }
//        
//        while (out.charAt(out.length()-1) == '#' || out.charAt(out.length()-1) == ',') out.deleteCharAt(out.length()-1);
//        return out.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data == "" || data == "#") return null;
//        
//        String []val = data.split(",");
//        int i = 1;
//        TreeNode root = new TreeNode(Integer.valueOf(val[0]).intValue());
//        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        
//        while (i < val.length && !queue.isEmpty()) {
//            TreeNode curr = queue.pop(); 
//            if ("#".equals(val[i])) curr.left = null;
//            else {
//                curr.left = new TreeNode(Integer.valueOf(val[i]).intValue());
//                queue.add(curr.left);
//            }
//            
//            i++;
//            if (i >= val.length) break;
//            if ("#".equals(val[i])) curr.right = null;
//            else {
//                curr.right = new TreeNode(Integer.valueOf(val[i]).intValue());
//                queue.add(curr.right);
//            }
//            i++;
//        }
//        return root;
//    }
//    
//    public static void main(String []args) {
//        Problem297 sol = new Problem297();
//        
//        TreeNode root = sol.new TreeNode(1);
//        root.left = sol.new TreeNode(2);
////        root.right = sol.new TreeNode(3);
////        
////        root.right.left = sol.new TreeNode(4);
////        root.right.right = sol.new TreeNode(5);
//        
//        String out = sol.serialize(root);
//        System.out.println(out);
//        
//        
//        TreeNode clone = sol.deserialize(out);
//        System.out.println(clone);
//    }
//    
//    
//}