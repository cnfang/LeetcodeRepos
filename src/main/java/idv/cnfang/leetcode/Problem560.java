package idv.cnfang.leetcode;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 560> Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

====================== Solution ======================

*/

public class Problem560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null)
            return 0;
        
        HashMap<Integer, Integer> sumCounter = new HashMap<>();
        // for the sum range between [0, current index]
        sumCounter.put(0, 1);
        
        int total = 0, acc = 0;
        for (int num: nums) {
            acc += num;
            // sum at index range of [index < current, current Index]
            total += sumCounter.getOrDefault(acc-k, 0);
            // put current sum to dictionary
            sumCounter.put(acc, sumCounter.getOrDefault(acc, 0) + 1);
        }
        
        return total;
    }
    
    @Test
    public void test_example1() {
        int []nums = {1, 1, 1};
        int k = 2;
        int expected = 2;
        Problem560 sol = new Problem560();
        assertEquals(expected, sol.subarraySum(nums, k));
    }
    
    @Test
    public void test_example2() {
        int []nums = {1};
        int k = 0;
        int expected = 0;
        Problem560 sol = new Problem560();
        assertEquals(expected, sol.subarraySum(nums, k));
    }
}