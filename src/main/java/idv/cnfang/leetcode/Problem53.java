package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 53> Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

====================== Solution ======================
apply DP method
*/

public class Problem53 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int poolMin = nums[0];
        int acc = nums[0];
        int maxSumOfSubArray = nums[0];
        for (int m = 1; m < nums.length; m++) {
            acc += nums[m];
            // maximum occurs at nums[m]
            if (nums[m] > maxSumOfSubArray)
                maxSumOfSubArray = nums[m];
            // maximum occurs at nums[j:m], where j is the index of minimum accumulated sum
            if (acc - poolMin > maxSumOfSubArray)
                maxSumOfSubArray = acc - poolMin;
            // maximum occurs at nums[0:m]
            if (acc > maxSumOfSubArray)
                maxSumOfSubArray = acc;
            poolMin = Math.min(poolMin, acc);
        }
        return maxSumOfSubArray;
    }
    
    public int maxSubArray_DP(int[] nums) {
        int n = nums.length;
        int currMax = nums[n-1];
        int max = currMax;
        
        for (int i = n - 2; i >= 0; i--) {
            currMax = Math.max(nums[i], currMax + nums[i]);
            if (currMax > max) max = currMax;
        }
        return max;
    }
    
    @Test
    public void test1() {
        int []nums = {-2,1,-3,4,-1,2,1,-5,4};
        int expected = 6; // sum of [4,-1,2,1]
        Problem53 sol = new Problem53();
        assertEquals(expected, sol.maxSubArray(nums));
    }
    
    @Test
    public void test2() {
        int []nums = {-2,1};
        int expected = 1; 
        Problem53 sol = new Problem53();
        assertEquals(expected, sol.maxSubArray(nums));
    }
    
    @Test
    public void test3() {
        int []nums = {2,1};
        int expected = 3;
        Problem53 sol = new Problem53();
        assertEquals(expected, sol.maxSubArray(nums));
    }
    
}