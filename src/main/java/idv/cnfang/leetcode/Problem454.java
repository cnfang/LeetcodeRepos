package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 454> Hash Table: 4Sum II

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) : A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) : A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

*/


public class Problem454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || B == null || C == null || D == null) return 0;
        
        // Method 1
        HashMap<Integer, Integer> comb1 = twoSum(A, B);
        HashMap<Integer, Integer> comb2 = twoSum(C, D);
        
        int count = 0;
        for (int key: comb1.keySet()) {
            if (!comb2.containsKey(-key)) continue;
            count = count + comb1.get(key)*comb2.get(-key);
        }
        return count;
    }
    
    /**
     * return sum mapping to how many combinations are there
     * @param x
     * @param y
     * @return
     */
    private HashMap<Integer, Integer> twoSum(int []x, int []y) {
        HashMap<Integer, Integer> dic = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> count1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> count2 = new HashMap<Integer, Integer>();
        for (int i = 0; i < x.length; i++) {
            count1.put(x[i], count1.getOrDefault(x[i], 0)+1);
            count2.put(y[i], count2.getOrDefault(y[i], 0)+1);
        }
        
        for (int key1: count1.keySet()) {
            for (int key2: count2.keySet()) {
                int key = key1 + key2;
                int value = count1.get(key1) * count2.get(key2);
                dic.put(key, dic.getOrDefault(key, 0)+value);
            }
        }
        return dic;
    }
    
    @Test
    public void test_fourSumCount() {
        int []A = {1, 2};
        int []B = {-2, -1};
        int []C = {-1, 2};
        int []D = {0, 2};
        
        Problem454 sol = new Problem454();
        int expected = 2;
        assertEquals(expected, sol.fourSumCount(A, B, C, D));
    }
}
// brute force
//public class Problem454 {
//    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
//        if (A == null || B == null || C == null || D == null) return 0;
//        Arrays.sort(A);
//        Arrays.sort(B);
//        Arrays.sort(C);
//        Arrays.sort(D);
//        
//        List<List<Integer>> sol = new ArrayList<List<Integer>>();
//        List<Integer> comb = new ArrayList<Integer>();
//        backtrack(sol, comb, A, B, C, D, 0, 0);
//        // System.out.println(sol);
//        return sol.size();
//    }
//    
//    private void backtrack(List<List<Integer>> sol, List<Integer> comb, int[] A, int[] B, int[] C, int[] D, int level, int target) {
//
//        
//        int n = A.length;
//        // base case
//        if (level == 3) {
//            int index = Arrays.binarySearch (D, target);
//            int first = index;
//            int last = index;
//            if (index < 0) return;
//            while (first > 0 && D[first-1] == target) first--;
//            while (last < n - 1 && D[last+1] == target) last++;
//            for (int i = first; i <= last; i++) {
//                comb.add(i);
//                sol.add(new ArrayList<Integer>(comb));
//                comb.remove(3);
//            }
//        }
//        
//        else if (level == 0) {
//            for (int i = 0; i < n; i++) {
//                comb.add(i);
//                backtrack(sol, comb, A, B, C, D, level+1, target-A[i]);
//                comb.remove(0);
//            }
//        }
//        else if (level == 1) {
//            for (int i = 0; i < n; i++) {
//                comb.add(i);
//                backtrack(sol, comb, A, B, C, D, level+1, target-B[i]);
//                comb.remove(1);
//            } 
//        }
//        else if (level == 2) {
//            for (int i = 0; i < n; i++) {
//                comb.add(i);
//                backtrack(sol, comb, A, B, C, D, level+1, target-C[i]);
//                comb.remove(2);
//            } 
//        }
//        
//        return;
//    }
//    
//    public static void main(String []args) {
//        int []A = {1, 2};
//        int []B = {-2, -1};
//        int []C = {-1, 2};
//        int []D = {0, 2};
//        
//        Problem454 sol = new Problem454();
//        System.out.println(sol.fourSumCount(A, B, C, D));
//    }
//}