package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
Leetcode <Problem 279> Queue & Stack : Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

====================== Solution ======================
1. backtracking method which is slows
2. dynamic programming method which is faster than backtracking
*/

public class Problem279 {
    public int numSquares(int n, String method) {
       // Method 1: backtrack
        if ("backtrack".equals(method)) {
            int []min = {n};
            int []square = squareInit(n);
            backtrack(square, min, n, 0);
            return min[0];
        }
        
        // Method 2: dynamic program
        int m = dynamicProgram(n);
        return m;
    }
    
    
    private int[] squareInit(int n) {
        int root = (int) Math.sqrt(n);
        int []square = new int[root];
        for (int val = root; val >= 1; val--)
            square[root-val] = val*val;
        return square;
    }
    
    
    private void backtrack(int []square, int []min, int target, int level) {
        // base case
        if (target == 0 && level < min[0]) {
            min[0] = level;
            return;
        }
        if (target > 0 && level >= min[0]) return;
       
        for (int val: square)
            if (val <= target)
                backtrack(square, min, target-val, level+1);
        return;
    }
    
    private int dynamicProgram(int n) {
        int []dp = new int[n+1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j*j <= i; j++)
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
        }
        
        return dp[n];
    }
    
    
    @ParameterizedTest
    @ValueSource(strings = {"backtrack", "dynamic program"})
    public void test_numSquares(String method) {
        int n = 9313;
        
        Problem279 sol = new Problem279();
        
        int result = sol.numSquares(n, "backtrack");
        int expected = 3;
    
        assertEquals(expected, result);
    }
}