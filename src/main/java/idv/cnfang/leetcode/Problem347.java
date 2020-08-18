package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 347> Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]


Example 2:
Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
*/


public class Problem347{
  
    public int[] topKFrequent(int []nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int key: nums)
            map.compute(key, (a, v) -> (v == null)? 1: v+1);
        
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet())
            queue.add(entry);
        
        int []ans = new int[k];
        for (int i = 0; i < k; i++)
            ans[i] = queue.poll().getKey();
        
        return ans;
    }
    
    
    @Test
    public void test1() {
        int []nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int []expected = {1, 2};
        Problem347 sol = new Problem347();
        assertArrayEquals(expected, sol.topKFrequent(nums, k));
    }
    @Test
    public void test2() {
        int []nums = {1};
        int k = 1;
        int []expected = {1};
        Problem347 sol = new Problem347();
        assertArrayEquals(expected, sol.topKFrequent(nums, k));
    }
}