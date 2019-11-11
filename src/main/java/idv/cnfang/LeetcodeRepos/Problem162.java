package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 162> Binary Search: Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.
*/

public class Problem162 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        
        int []idxPeak = {-1};
        peakExist(idxPeak, nums, 0, nums.length);
        return idxPeak[0];
    }
    
    private boolean peakExist(int []idxPeak, int []nums, int start, int end) {
        if (start > end) return false;
        
        if (start == 0 && nums[0] > nums[1]) {
            idxPeak[0] = 0;
            return true;
        }
        else if (end == nums.length && nums[nums.length-1] > nums[nums.length-2])
        {
            idxPeak[0] = nums.length-1;
            return true;
        }
        else if (start > 0 && nums[start] > nums[start+1] && nums[start] > nums[start-1]) {
            idxPeak[0] = start;
            return true;
        }
        else if (end < nums.length-1 && nums[end] > nums[end+1] && nums[end] > nums[end-1]) {
            idxPeak[0] = end;
            return true;
        }
        
        int mid = start + (end-start)/2;
        if (peakExist(idxPeak, nums, start+1, mid)) return true;
        return peakExist(idxPeak, nums, mid, end-1);
    }
    
    @Test
    public void testfindPeakElement() {
        int []nums = {1,2,3,4,3};
        Problem162 sol = new Problem162();
        int expected = 3;
        assertEquals(expected, sol.findPeakElement(nums));
    }
}
