package idv.cnfang.leetcode;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 260> Single Number II

Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
Input: [2,2,3,2]
Output: 3


Example 2:
Input: [0,1,0,1,0,1,99]
Output: 99

====================== Solution ======================
1. (slow) using set to check duplicate elements
2. (recommend, fast) bitwise operation
https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/542/week-4-june-22nd-june-28th/3368/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers

*/

public class Problem260 {
    public int singleNumber(int[] nums) {
        HashSet<Integer> candidates = new HashSet<>();
        HashSet<Integer> notCandidates = new HashSet<>();
        
        for (int num: nums) {
            if (notCandidates.contains(num))
                continue;
            else if (candidates.contains(num)) {
                candidates.remove(num);
                notCandidates.add(num);
            } else
                candidates.add(num);
        }
        
        return candidates.iterator().next();
    }
    
  
    
    @Test
    public void example1() {
        int []nums = {2,2,3,2};
        Problem260 sol = new Problem260();
        int expected = 3;
        assertThat(sol.singleNumber(nums), is(expected));
    }
    
    @Disabled @Test
    public void example2() {
        int []nums = {0,1,0,1,0,1,99};
        Problem260 sol = new Problem260();
        int expected = 99;
        assertThat(sol.singleNumber(nums), is(expected));
    }
}