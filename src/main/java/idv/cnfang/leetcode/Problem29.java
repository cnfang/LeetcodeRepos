package idv.cnfang.leetcode;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 29> Divide Two Integers

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3


Example 2:
Input: dividend = 7, divisor = -3
Output: -2

Note:
1. Both dividend and divisor will be 32-bit signed integers.
2. The divisor will never be 0.
3. Assume we are dealing with an environment which could only store integers within the 32-bit signed 
   integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 
   2^31 − 1 when the division result overflows.

================= Solution =================
1. Key is to use long to avoid overflow.
2. mind type cast on bit shift
*/

public class Problem29 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        
        boolean positive = Integer.signum(dividend) * Integer.signum(divisor) > 0;
        
        long quotient = getQuotient(Math.abs((long)dividend), Math.abs((long)divisor));
        
        long actual = positive? quotient: -quotient;
        
        if (actual < (long)Integer.MIN_VALUE) 
            return Integer.MAX_VALUE;
        else if (actual > (long) Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int) actual;
    }
    
    public long getQuotient(long dividend, long divisor) {
        if (dividend < divisor) 
            return 0;
        int x;
        long sum;
        for (x = 1; x < 32; x++) {
            sum = divisor << x;
            if (sum > dividend) 
                break;
        }
        sum = divisor << (--x);
        return ((long)1 << x)  + getQuotient(dividend - sum, divisor);
    }
    
    @Test
    public void example1() {
        int dividend = 10;
        int divisor = 3;
        int expected = 3;
        Problem29 sol = new Problem29();
        int ans = sol.divide(dividend, divisor);
        assertThat(ans, equalTo(expected));
    }
    
    @Test
    public void example2() {
        int dividend = 7;
        int divisor = -3;
        int expected = -2;
        Problem29 sol = new Problem29();
        int ans = sol.divide(dividend, divisor);
        assertThat(ans, equalTo(expected));
    }
    
    @Test
    public void example3() {
        int dividend = -2147483648;
        int divisor = -1;
        int expected = 2147483647;
        Problem29 sol = new Problem29();
        int ans = sol.divide(dividend, divisor);
        assertThat(ans, equalTo(expected));
    }
}
