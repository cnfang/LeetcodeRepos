package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 216> Combination Sum III

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.


Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]


Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

public class Problem216 {
    
private int[] upperLimit;
    
    private void generateUpperLimit(int k) {
        upperLimit = new int[k+1];
        int sum = 0, start = 1;
        for (int max=9, idx=1; idx <= k; idx++, max--) {
            sum += max;
            upperLimit[idx] = sum;
        } 
        return;
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n < (((1+k)*k)>>1) || k <= 0)
            return Arrays.asList();
        
        generateUpperLimit(k);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), k, n);
        return ans;
    }
    
    private void backtrack(List<List<Integer>> ans,
                             List<Integer> prefix, 
                             int k, 
                             int n) {
        if (k == 0) {
            if (n == 0)
                ans.add(new ArrayList<>(prefix));
            return;
        }
        
        if (n > upperLimit[k])
            return;
        
        int minV = prefix.isEmpty()? 1: prefix.get(prefix.size()-1)+1;
        for (int candidate = minV; candidate < 10; candidate++) {
            prefix.add(candidate);
            backtrack(ans, prefix, k-1, n-candidate);
            prefix.remove(prefix.size()-1);
        }
        return;
    }
    
    @Test
    public void test_Case1() {
        Problem216 sol = new Problem216();
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1,2,4));
        assertEquals(expected, sol.combinationSum3(3, 7));
    }
    
    @Test
    public void test_Case2() {
    	Problem216 sol = new Problem216();
    	List<List<Integer>> expected = Arrays.asList(Arrays.asList(1,2,6),
    								   				 Arrays.asList(1,3,5),
    								   				 Arrays.asList(2,3,4));
        assertEquals(expected, sol.combinationSum3(3, 9));
    }
}
