package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 209> Array & String: Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 

=============================================
1. two pointers method 
*/

public class Problem209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int start = 0;  // include
        int end = 0;    // exclude
        int sum = 0;
        int numEle = Integer.MAX_VALUE;
        
        while (end < len && start <= end && numEle > 1) {
            while (sum < s && end < len)  { 
                sum += nums[end];
                end++;
            }
            
            if (sum >= s && end-start < numEle) numEle = end - start;
            
            
            while (sum >= s && start < end) {
                sum -= nums[start];
                start += 1;
                if (sum >= s && end-start < numEle) numEle = end - start;
            }
            
        }
        
        if (numEle == Integer.MAX_VALUE) return 0;
        return numEle;
    }
    
   
    @Test
    public void testminSubArrayLen() {
        int []nums = {2,3,1,2,4,3};
        Problem209 sol = new Problem209();
        int expected = 2;
        assertEquals(expected, sol.minSubArrayLen(7, nums));
    }
}