package idv.cnfang.leetcode;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
Leetcode <Problem 62> Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:
Input: m = 7, n = 3
Output: 28

================ Solution
Factorial
*/

public class Problem62 {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) 
            return 0;
        
        int right = n - 1, down = m - 1;
        int total = right + down;
        long ans = 0, numerator = 0;
        
        if (right >= down) {
            numerator = factorial(right+1, total);
            ans = numerator / factorial(1, down); 
        } else {
            numerator = factorial(down+1, total);
            ans = numerator / factorial(1, right); 
        }
        
        return (int)ans;
    }
    
    private static long factorial(int start, int end) {
//        return LongStream
//               .rangeClosed(start, end)
//               .reduce(1, (long x, long y) -> x * y);
        long ans = 1;
        for (int i = start; i <= end; i++)
            ans *= i;
        return ans;
    }
    
    @Test
    public void example1() {
        Problem62 sol = new Problem62();
        int expected = 3;
        assertThat(sol.uniquePaths(3, 2), equalTo(expected));
    }
    
    @Test
    public void example2() {
        Problem62 sol = new Problem62();
        int expected = 193536720;
        assertThat(sol.uniquePaths(23, 12), equalTo(expected));
    }
     
}
