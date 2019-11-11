package idv.cnfang.leetcode;
import java.util.*;

/**
Leetcode <Problem 46> Recursion II : Permutations

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

====================== Solution ======================
apply backtracking method to rule out impossible cases

*/
public class Problem46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        List<Integer> prefix = new ArrayList<Integer>();
        // check[index] is to see if nums[index] is available to take or not
        boolean []check = new boolean[nums.length];
     
        backtrack(nums, solution, prefix, check, nums.length);
        return solution;
    }
    
    /**
     * search all possible combinations of nums
     * @param solution
     * @param prefix
     * @param lookbook
     * @param k
     */
    private void backtrack(int []book, List<List<Integer>> solution, List<Integer> prefix, boolean []check, int k) {
        // base case: stop searching
        if (k == 0) {
            solution.add(new ArrayList<Integer>(prefix));
            return;
        }
        
        for (int index = 0; index < book.length; index++) {
            // place number
            if (check[index]) continue;
            prefix.add(book[index]);
            check[index] = true;
            
            // backtrack: next level search
            backtrack(book, solution, prefix, check, k-1);
            
            // remove number
            prefix.remove(prefix.size()-1);
            check[index] = false;
        }
        return;
    }
    
    public static void main(String []args) {
        int []nums = {1, 2, 3};
        Problem46 sol = new Problem46();
        List<List<Integer>> perm = sol.permute(nums);
        System.out.println(perm);
    }
}

// slow... using hashmap
//class Problem46 {
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> solution = new ArrayList<List<Integer>>();
//        List<Integer> prefix = new ArrayList<Integer>();
//        HashMap<Integer, Boolean> lookbook = new HashMap<Integer, Boolean>();
//        bookInitialization(lookbook, nums);
//        
//        backtrack(solution, prefix, lookbook, nums.length);
//        return solution;
//    }
//    /**
//     * initialize all the keys to false, true means key being taken, false means key available
//     * @param lookbook
//     * @param keys
//     */
//    private void bookInitialization( HashMap<Integer, Boolean> lookbook, int []keys) {
//        for (int key: keys)
//            lookbook.put(key, false);
//        return;
//    }
//    
//    /**
//     * search all possible combinations of nums
//     * @param solution
//     * @param prefix
//     * @param lookbook
//     * @param k
//     */
//    private void backtrack(List<List<Integer>> solution, List<Integer> prefix, HashMap<Integer, Boolean> lookbook, int k) {
//        // base case: stop searching
//        if (k == 0) {
//            solution.add(new ArrayList<Integer>(prefix));
//            return;
//        }
//        
//        for (int key: lookbook.keySet()) {
//            // place number
//            if (lookbook.get(key)) continue;
//            prefix.add(key);
//            lookbook.replace(key, true);
//            
//            // backtrack: next level search
//            backtrack(solution, prefix, lookbook, k-1);
//            
//            // remove number
//            prefix.remove(prefix.size()-1);
//            lookbook.replace(key, false);
//        }
//        return;
//    }
//    
//    public static void main(String []args) {
//        int []nums = {1, 2, 3};
//        Problem46 sol = new Problem46();
//        List<List<Integer>> perm = sol.permute(nums);
//        System.out.println(perm);
//    }
//}

// slow solution of using arraylist
//class Problem46 {
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> solution = new ArrayList<List<Integer>>();
//        List<Integer> prefix = new ArrayList<Integer>();
//        List<Integer> candidate = Arrays.stream(nums).boxed().collect(Collectors.toCollection(ArrayList::new));
//        
//        backtrack(solution, prefix, candidate, candidate.size());
//        return solution;
//    }
//    
//    private void backtrack(List<List<Integer>> solution, List<Integer> prefix, List<Integer> candidate, int k) {
//        // base case: stop searching
//        if (k == 0) {
//            solution.add(new ArrayList<Integer>(prefix));
//            return;
//        }
//        
//        int n = candidate.size();
//        for (int index = 0; index < n; index++) {
//            // place number
//            Integer pop = candidate.remove(index);
//            prefix.add(pop);
//            
//            // backtrack: next level search
//            backtrack(solution, prefix, candidate, k-1);
//            
//            // remove number
//            prefix.remove(prefix.size()-1);
//            candidate.add(index, pop);
//        }
//        return;
//    }
//    
//    public static void main(String []args) {
//        int []nums = {1, 2, 3};
//        Problem46 sol = new Problem46();
//        List<List<Integer>> perm = sol.permute(nums);
//        System.out.println(perm);
//    }
//}
