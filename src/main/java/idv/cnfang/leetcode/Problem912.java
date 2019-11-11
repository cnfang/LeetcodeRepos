package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 912> Recursion II : Sort an Array

Given an array of integers nums, sort the array in ascending order.
Example 1:

Input: [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
*/
       
public class Problem912 {
    public int[] sortArray(int[] nums) {
        Queue<List<Integer>> subProb = new ArrayDeque<List<Integer>>();
        for (int val: nums) subProb.add(Arrays.asList(val));
        return merge(subProb).remove().stream().mapToInt(i->i).toArray();
    }
    
    private Queue<List<Integer>> merge(Queue<List<Integer>>subProb) {
        if (subProb.size() <= 1) return subProb;
        Queue<List<Integer>> res = new ArrayDeque<List<Integer>>();
        
        List<Integer> left, right, sum;
        int i,j;
        while (subProb.size() >= 2) {
            sum = new ArrayList<Integer>();
            i = 0;
            j = 0;
            left = subProb.remove();
            right = subProb.remove();
            while (i < left.size() && j < right.size())
                if (left.get(i) <= right.get(j)) sum.add(left.get(i++));
                else sum.add(right.get(j++));
            while (i < left.size()) sum.add(left.get(i++));
            while (j < right.size()) sum.add(right.get(j++));
            res.add(sum);
        }
        if (subProb.size() == 1) res.add(subProb.remove());
        return merge(res);
        
    }
    
    @Test
    public void test_sortArray() {
        int []nums = {5, 2, 3, 1};
        Problem912 sol = new Problem912();
        
        int []expected = {1,2,3,5};
        assertArrayEquals(expected, sol.sortArray(nums));
    }
}