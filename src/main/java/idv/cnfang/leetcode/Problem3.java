package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 3> Hash Table: Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/

public class Problem3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int start = 0;
        int end = 0;
        int n = s.length();
        int maxN = Integer.MIN_VALUE;
        char []array = s.toCharArray();
        HashSet<Character> bag = new HashSet<Character>();
        
        while (start <= end && end < n) {
            while (end < n && bag.add(array[end])) {
                end++;
            }
            if (bag.size() > maxN) maxN = bag.size();
            while (end < n && array[start] != array[end]) {
                bag.remove(array[start]);
                start += 1;
            }
            bag.remove(array[start]);
            start += 1;
        }
        
        return maxN;
    }
    
  
    @Test
    public void test_lengthOfLongestSubstring() {
        String s = "pwwkew";
        Problem3 sol = new Problem3();
        int expected = 3;
        assertEquals(expected, sol.lengthOfLongestSubstring(s));
    }
}