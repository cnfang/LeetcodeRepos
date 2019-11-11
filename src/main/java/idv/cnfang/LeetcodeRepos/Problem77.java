package idv.cnfang.leetcode;

import java.util.*;
/**
Leetcode <Problem 77> Recursion II : Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

====================== Solution ======================
apply backtracking method to rule out impossible cases

*/

public class Problem77 {
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        ArrayList<Integer> prefixSol = new ArrayList<Integer>();
        backtrack(solution, prefixSol, n, k, 1);
        return solution;
    }
    
    private void backtrack(List<List<Integer>> solution, List<Integer> prefixSol, int n, int k, int startVal) {
        // find a solution
        if (k == 0) {
            solution.add(new ArrayList<Integer>(prefixSol));
            return;
        }
        // base case: no need to search more solutions
        if (startVal > n || k < 1 || (n - startVal + 1) < k) {
            return;
        }
        
        for (int num = startVal; num <= n-k+1; num++) {
            place_number(prefixSol, num);
            backtrack(solution, prefixSol, n, k-1, num+1);
            remove_number(prefixSol);
        }
        
        return;
    }
    
    private void place_number(List<Integer> prefixSol, int num) {
        prefixSol.add(num);
        return;
    }
    
    private void remove_number(List<Integer> prefixSol) {
        prefixSol.remove(prefixSol.size()-1);
        return;
    }
    
    public static void main(String []args) {
        Problem77 sol = new Problem77();
        List<List<Integer>> comb = sol.combine(4, 3);
        System.out.println(comb);
    }
    
}