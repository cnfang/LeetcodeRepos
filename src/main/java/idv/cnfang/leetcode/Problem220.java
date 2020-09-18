package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 220> Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true


Example 2:
Input: nums = [1,0,1,1], k = 1, t = 2
Output: true


Example 3:
Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false

=============== Solution ===============
1. [mine] sort the array and check value of range t if any element with index difference less than k exists
2. [BST] use BST to maintain window of size k, add and remove element into window while iterating the array
*/

public class Problem220 {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1)
            return false;
        if (k <= 0 || t < 0)
            return false;
        
        Integer[] sidxArr = IntStream.range(0, nums.length).boxed().toArray(Integer[]::new);
        
        // sidx stores the index of sorted element
        Arrays.sort(sidxArr, (i, j) -> Integer.compare(nums[i], nums[j]));
        Arrays.sort(nums);
        
        int end;
        for (int i = 0; i < nums.length; i++) {
            end = Arrays.binarySearch(nums, i, nums.length, nums[i]+t);
            end = end >= 0? end: -end-2;
            // get the rightest element same value as nums[end]
            // since Arrays.binarySearch doesn't guarantee to return the largest index for the elements
            // with specified value
            while (end + 1 < nums.length && nums[end+1] == nums[end])
                end += 1;
            for (int j = i+1; j <= end; j++)
                if (Math.abs(sidxArr[j] - sidxArr[i]) <= k)
                    return true;
        }
        return false;
    }
    
    
    @Test
    public void test1() {
        int []nums = {1,2,3,1};
        Problem220 sol = new Problem220();
        assertTrue(sol.containsNearbyAlmostDuplicate(nums, 3, 0));
    }
    
    @Test
    public void test2() {
        int []nums = {1,0,1,1};
        Problem220 sol = new Problem220();
        assertTrue(sol.containsNearbyAlmostDuplicate(nums, 1, 2));
    }
    
    @Test
    public void test3() {
        int []nums = {1,5,9,1,5,9};
        Problem220 sol = new Problem220();
        assertFalse(sol.containsNearbyAlmostDuplicate(nums, 2, 3));
    }
    
    @Test
    public void test4() {
        int []nums = {-1,-1};
        Problem220 sol = new Problem220();
        assertFalse(sol.containsNearbyAlmostDuplicate(nums, 1, -1));
    }
}