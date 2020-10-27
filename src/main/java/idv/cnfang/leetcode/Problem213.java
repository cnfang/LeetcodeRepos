package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 213> House Robber II

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.


Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.


Example 3:
Input: nums = [0]
Output: 0
 

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
==============================================


*/

public class Problem213 {
	// case 1: [include, .... exclude]
    // case 2: [exclude, .... include]
	
	public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(getMax(nums, true, false), getMax(nums, false, true));
    }
    
    private int getMax(int []nums, boolean startInclude, boolean endInclude) {
        int []amount = new int[nums.length];
        int end = endInclude? nums.length: nums.length-1;
        amount[0] = startInclude? nums[0]: 0;
        int max = amount[0];
        
        for (int i = 1; i < end; i++) {
            int pre3 = i-3 >= 0? amount[i - 3]: 0;
            int pre2 = i-2 >= 0? amount[i - 2]: 0;
            
            amount[i] = Math.max(pre2, pre3) + nums[i];
            max = Math.max(max, amount[i]);
        }
        
        return max;
    }
    
    @Test
    public void test() {
        Problem213 sol = new Problem213();
        assertEquals(3, sol.rob(new int[] {2,3,2}));
        assertEquals(4, sol.rob(new int[] {1,2,3,1}));
        assertEquals(0, sol.rob(new int[] {0}));
    }
}
