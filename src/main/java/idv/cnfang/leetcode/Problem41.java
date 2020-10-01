package idv.cnfang.leetcode;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
Leetcode <Problem 41> First Missing Positive

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3


Example 2:
Input: [3,4,-1,1]
Output: 2


Example 3:
Input: [7,8,9,11,12]
Output: 1
Follow up:

Your algorithm should run in O(n) time and uses constant extra space.
===========================
use index concept
Time O(3n)
*/

public class Problem41 {
    
	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
            return 1;
		
		// ignore the negative number
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0)
                nums[i] = nums.length;
            else if (nums[i] == nums.length)
                ans = nums.length + 1;
        }
        
        // mark visited index
        for (int i = 0; i < nums.length; i++) {
            int location = Math.abs(nums[i]);
            if (location >= nums.length)
                continue;
            
            nums[location] = nums[location] > 0? -nums[location]: nums[location];
        }
        
        // search the index with no mark
        for (int i = 1; i < nums.length; i++)
            if (nums[i] >= 0) {
                ans = i;
                break;
            }
        return ans;
    }
    
    @Test 
    public void test() {
        Problem41 sol = new Problem41();
        assertEquals(3, sol.firstMissingPositive(new int[] {1,2,0}));
        assertEquals(2, sol.firstMissingPositive(new int[] {3,4,-1,1}));
        assertEquals(1, sol.firstMissingPositive(new int[] {7,8,9,11,12}));
        assertEquals(1, sol.firstMissingPositive(new int[] {}));
    }
}


