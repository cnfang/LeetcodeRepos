package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 367> Binary Search: Valid Perfect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false
*/

public class Problem367 {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int r = num % 10;
        if (r == 2 || r == 3 || r == 7 || r == 8) return false;
        int start = 1;
        int end = num/2;
        
        while (end > start) {
            int mid = (start+end)/2;
            long square = mid*mid;
            if (square == num) return true;
            else if(square > num) end = mid-1;
            else start = mid+1;
        }
        if (end == start && end*end == num) return true;
        return false;
    }
    
    @Test
    public void test_isPerfectSquare() {
        Problem367 sol = new Problem367();
        assertFalse(sol.isPerfectSquare(808201));
    }
}