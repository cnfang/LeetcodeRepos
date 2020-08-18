package idv.cnfang.leetcode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**

Leetcode <Problem 342> Power of Four
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:
Input: 16
Output: true


Example 2:
Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?

=============== Solution ============

*/

public class Problem342 {
    public boolean isPowerOfFour(int num) {
        if (num < 1)
            return false;
       
        while (num >= 4) {
            if (!isMultipleOfFour(num))
                return false;
            num = num >> 2;
        }
        return num == 1? true: false;
    }
    
    private boolean isMultipleOfFour(int num) {
        for (int i = 0; i < 2; i++) {
            if ((num & 1) == 1)
                return false;
            num = num >> 1;
        }
        return true;
    }
    
    
    
    @Test
    public void example() {
        Problem342 sol = new Problem342();
        assertThat(sol.isPowerOfFour(16), equalTo(true));
        assertThat(sol.isPowerOfFour(5), equalTo(false));
    }
}