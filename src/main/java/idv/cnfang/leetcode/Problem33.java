package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 33> Search in Rotated Sorted Array


Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).


Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4


Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

============================
Binary Search
(a) apply regular binary search if selected block is ordered sequence
(b) search whole block if selected block is disordered sequence

*/

public class Problem33 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        
        return binarySearch(nums, target, 0, nums.length-1);
    }
    
    private int binarySearch(int []nums, int target, int start, int end) {
        // base case
        if (start > end) 
            return -1;
        
        int middle = Math.floorDiv(start + end, 2);
        
        if (nums[middle] == target)
            return middle;
        
        // disorder seq
        else if (nums[start] > nums[end]) {
            int idx = binarySearch(nums, target, start, middle-1);
            if (idx != -1)
                return idx;
            return binarySearch(nums, target, middle+1, end);
        }
        
        // order seq
        else {
            if (target > nums[middle])
                return binarySearch(nums, target, middle+1, end);
            else
                return binarySearch(nums, target, start, middle-1);
        }
    }
    
  
    @Test
    public void test_example1() {
        int []nums = {4,5,6,7,0,1,2};
        int target = 0;
        int expected = 4;
        Problem33 sol = new Problem33();
        assertEquals(expected, sol.search(nums, target));
    }
    
    @Test
    public void test_example2() {
        int []nums = {4,5,6,7,0,1,2};
        int target = 3;
        int expected = -1;
        Problem33 sol = new Problem33();
        assertEquals(expected, sol.search(nums, target));
    }
}