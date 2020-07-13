package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 15> 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

public class Problem15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return null;
        
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        
        for (int i = 0; i < nums.length-1; i++)
            for (int j = i+1; j < nums.length; j++) {
                int key = -(nums[i] + nums[j]);
                int idx = Arrays.binarySearch(nums, j+1, nums.length, key);
                if (idx < 0)
                    continue;
                List<Integer> tmp = new ArrayList<>();
                tmp.add(nums[i]);
                tmp.add(nums[j]);
                tmp.add(nums[idx]);
                ans.add(tmp);
            }
        
        List<List<Integer>> re = new ArrayList<>(ans);
        return re;
    }
    
    
    @Test
    public void example1() {
        int []nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1, 0, 1));
        expected.add(Arrays.asList(-1, -1, 2));
        
        Problem15 sol = new Problem15();
        List<List<Integer>> ans = sol.threeSum(nums);
        assertThat(ans, containsInAnyOrder(expected.toArray()));
    }
    
}


