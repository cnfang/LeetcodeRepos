package idv.cnfang.leetcode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**

Leetcode <Problem 338> Counting Bits
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:
Input: 2
Output: [0,1,1]


Example 2:
Input: 5
Output: [0,1,1,2,1,2]


Follow up:
It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

=============== Solution ============
using DP, with recursive relation: getNumOfOne(num) = getNumOfOne(num >> 1) + (lastBit == 1 ? 1: 0)
with base base getNumOfOne(0) = 0;

e.g. num = 5 (b101)
getNumOfOne(5) = getNumOfOne(b101) 
               = getNumOfOne(b10) + 1
               = getNumOfOne(2) + 1

getNumOfOne(2) = getNumOfOne(b10)
               = getNumOfOne(b1) + 0

getNumOfOne(1) = getNumOfOne(b1)
               = getNumOfOne(b0) + 1
               = 1;
               
               
e.g. num = 6 (b110)
getNumOfOne(b110) = getNumOfOne(b11) + 0
                  = getNumOfOne(3)
getNumOfOne(3)    = getNumOfOne(b11)
                  = getNumOfOne(b1) + 1
*/

public class Problem338 {
    public int[] countBits(int num) {
        if (num == 0)
            return new int []{0};
        else if (num == 1)
            return new int []{0, 1};
        
        
        int []map = new int [num+1];
        map[0] = 0;
        for (int i = 1; i < num+1; i++)
            map[i] = (1 & i) + map[i >> 1];
        
        return map;
    }
    
    
    
    @Test
    public void example1() {
        Problem338 sol = new Problem338();
        int num = 4;
        int []expected = {0,1,1,2,1};
        
        assertThat(sol.countBits(num), equalTo(expected));
    }
}