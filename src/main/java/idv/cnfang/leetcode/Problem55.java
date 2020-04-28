package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
Leetcode <Problem 55> Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.


Example 2:
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

============================== Solution ========================
1. (fast) DP, O(n) for time 
- The idea is to record the largest index that could reach the final index

2. (slow) backtracking, O(n^2)

*/

public class Problem55 {
    public boolean canJump(int[] nums) {
        if (nums == null)
            return false;
        if (nums.length == 1)
            return true;
        
        // trueIndex is to mark the index 
        // that is larger than current index and final element reachable
        int reachableIndex = nums.length - 1;
        
        for (int index = nums.length - 2; index >= 0; index--) {
            if (index + nums[index] >= reachableIndex)
                reachableIndex = index;
        }
        
        return reachableIndex == 0;
    }
    
    public boolean canJump_backtrack(int[] nums) {
        if (nums == null)
            return false;
        if (nums.length == 1)
            return true;
        
        boolean []reached = new boolean [nums.length];
        int finalIdx = nums.length - 1;
        
        for (int index = finalIdx - 1; index >= 0; index--) {
            if (index + nums[index] >= finalIdx)
                reached[index] = true;    
            
            else {
                boolean ans = false;
                // check if true exists in the reached array within range of [index+1, index+step]
                // This is where we could improve performance. By remember the smallest index that is 
                // reachable to final element, we don't need extra reached array. see dp solution
                for (int step = nums[index]; step >= 1; step--)
                    ans = ans | reached[index+step];
                reached[index] = ans;
            }   
        }
        
        return reached[0];
    }
    
    @Test
    public void test_example1() {
        int []nums = {2,3,1,1,4};
        Problem55 sol = new Problem55();
        assertTrue(sol.canJump(nums));
    }
    
    @Test
    public void test_example2() {
        int []nums = {3,2,1,0,4};
        Problem55 sol = new Problem55();
        assertFalse(sol.canJump(nums));
    }
}