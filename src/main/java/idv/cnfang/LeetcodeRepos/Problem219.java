package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 219> Contains Duplicate II

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true


Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true


Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

public class Problem219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        
        
        HashMap<Integer, Integer> indexDic = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (indexDic.containsKey(nums[i])) {
                int lastIndex = indexDic.get(nums[i]);
                if (i - lastIndex <= k) return true;
                indexDic.replace(nums[i], i);
            }
            else {
                indexDic.put(nums[i], i);
            }
        }
        
        return false;
        
    }
    
    
    @Test
    public void testcontainsNearbyDuplicate() {
        int []nums = {1, 0, 1, 1};
        Problem219 sol = new Problem219();
        assertTrue(sol.containsNearbyDuplicate(nums, 1));
    }
}