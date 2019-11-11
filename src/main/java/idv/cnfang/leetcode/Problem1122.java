package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;




/**
Leetcode <Problem 1122> Relative Sort Array

Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
 

Constraints:

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
Each arr2[i] is distinct.
Each arr2[i] is in arr1.

*/


public class Problem1122 {
    // TreeMap to store the count: slow on 42.33% Java submission
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return null;
        
        int []ans = new int[arr1.length];
     
        TreeMap<Integer, Integer> lookup = new TreeMap<>();
        for (int key: arr1) {
            lookup.putIfAbsent(key, 0);
            lookup.replace(key, lookup.get(key) + 1);
        }
        
        int idx = 0;
        int idx2 = 0;
        while (idx < arr1.length && idx2 < arr2.length) {
            int key = arr2[idx2++];
            int count = lookup.get(key);
            lookup.remove(key);
            
            for(int i = 0; i < count; i++) ans[idx++] = key;
        }
        
        for (int key: lookup.keySet())
            for(int i = 0; i < lookup.get(key); i++) ans[idx++] = key;
        
        return ans;
    }
    
    
    @Test
    public void testrelativeSortArray() {
        int []arr1 = {2,3,1,3,2,4,6,19,9,2,7};
        int []arr2 = {2,1,4,3,9,6};
        
        Problem1122 sol = new Problem1122();
        int []result = sol.relativeSortArray(arr1, arr2);
        int []expected = {2,2,2,1,4,3,3,9,6,7,19};
        assertArrayEquals(expected, result);
    }
}