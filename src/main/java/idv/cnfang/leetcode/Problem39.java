package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 39> Combination Sum

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.


Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]


Example 3:
Input: candidates = [2], target = 1
Output: []


Example 4:
Input: candidates = [1], target = 1
Output: [[1]]


Example 5:
Input: candidates = [1], target = 2
Output: [[1,1]]
 

Constraints:
1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500

====================== Solution ======================

*/

public class Problem39 {
//	private int []candidates;
//	
//    private Map<Integer, List<List<Integer>>> map = new HashMap<>();
//    
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        this.candidates = candidates;
//        
//        Arrays.sort(this.candidates);
//        
//        map.put(0, Arrays.asList(Arrays.asList()));
//    
//        return backtrack(target);
//    }
//    
//    private List<List<Integer>> backtrack(int target) {
//        
//        if (map.get(target) != null)
//            return map.get(target);
//        
//        List<List<Integer>> sol = new ArrayList<>();
//        
//        for (int candidate: this.candidates) {
//            
//            int key = target - candidate;
//            
//            if (key < 0) break;
//            
//            List<List<Integer>> combines = backtrack(key);
//            
//            combines.forEach(item -> {
//            	if (item.size() == 0 || item.get(item.size()-1) <= candidate) {
//	                List<Integer> tmp = new ArrayList<>(item);
//	                tmp.add(candidate);
//	                sol.add(tmp);
//            	}
//            });
//        }
//        
//        map.put(target, sol);
//        return map.get(target);
//    }
    
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        
        map.put(0, Arrays.asList(Arrays.asList()));
        
        for (int num = 1; num <= target; num++) {
        	
        	List<List<Integer>> comb = new ArrayList<>();
        	
        	for (int candidate: candidates) {
        		
        		int key = num - candidate;
        		
        		if (key < 0)
        			break;
        		
        		for (List<Integer> item: map.get(key)) {
        			if (item.size() > 0 && item.get(item.size()-1) > candidate)
        				break;
        			List<Integer> tmp = new ArrayList<>(item);
        			tmp.add(candidate);
        			comb.add(tmp);
        		}
        	}
        	
        	map.put(num, comb);
        }
        
        return map.get(target);
    }
	
    @Test
    public void test1() {
    	Problem39 sol = new Problem39();
    	List<List<Integer>> ans = Arrays.asList(Arrays.asList(2,2,3),
    											Arrays.asList(7));
    	assertEquals(ans, sol.combinationSum(new int [] {2,3,6,7}, 7));
    }
    
    @Test
    public void test2() {
    	Problem39 sol = new Problem39();
    	List<List<Integer>> ans = Arrays.asList(Arrays.asList(2,2,2,2),
    											Arrays.asList(2,3,3),
    											Arrays.asList(3,5));
    	assertEquals(ans, sol.combinationSum(new int [] {2,3,5}, 8));
    }
    
    @Test
    public void test3() {
    	Problem39 sol = new Problem39();
    	List<List<Integer>> ans = Arrays.asList();
    	assertEquals(ans, sol.combinationSum(new int [] {2}, 1));
    }
    
    @Test
    public void test4() {
    	Problem39 sol = new Problem39();
    	List<List<Integer>> ans = Arrays.asList(Arrays.asList(1));
    	assertEquals(ans, sol.combinationSum(new int [] {1}, 1));
    }
    
    @Test
    public void test5() {
    	Problem39 sol = new Problem39();
    	List<List<Integer>> ans = Arrays.asList(Arrays.asList(1,1));
    	assertEquals(ans, sol.combinationSum(new int [] {1}, 2));
    }
}