package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

/**
Leetcode <Problem 78> Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

==================== Solution
apply backtracking

*/

public class Problem78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> collection = new ArrayList<List<Integer>>();
        backtracking(nums, collection, new ArrayList<>(), 0);
        return collection;
    }
    
    @SuppressWarnings("unchecked")
    private void backtracking(int[] nums, List<List<Integer>> collection, ArrayList<Integer> currList, int currIndex) {
        // base case
        if (currIndex == nums.length) {
            collection.add((ArrayList<Integer>) currList.clone());
            return;
        }
        
        // add num to list
        currList.add(nums[currIndex]);
        backtracking(nums, collection, currList, currIndex+1);
        
        // not add to list
        currList.remove(currList.size()-1);
        backtracking(nums, collection, currList, currIndex+1);
        return;
    }
    
    @Test
    public void test() {
        int []nums = {1,2,3};
        Problem78 sol = new Problem78();
        assertThat(sol.subsets(nums), containsInAnyOrder(Arrays.asList(),
                                                         Arrays.asList(1),
                                                         Arrays.asList(2),
                                                         Arrays.asList(3),
                                                         Arrays.asList(1,2),
                                                         Arrays.asList(1,3),
                                                         Arrays.asList(2,3),
                                                         Arrays.asList(1,2,3)));
    }
    
}
