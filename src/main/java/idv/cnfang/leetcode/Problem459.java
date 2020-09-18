package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 459> Repeated Substring Pattern

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.


Example 2:
Input: "aba"
Output: False


Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

=============== Solution ==============
1. [mine]check all the combination of reasonable substring using factors
2. [KMP]
3. [leetcode sharing] check if (s+s)[1:-1].contains(s)
*/


public class Problem459 {
	public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0)
            return true;
        if (s.length() == 1)
            return false;
        
        // get factors of s.length() except 1
        List<Integer> factors = getFactor(s.length());
        
        char []sArr = s.toCharArray();
        for(int k: factors) {
            // check if string is repeat of substring in length k
            if (isSubstringOfLengthK(sArr, k))
                return true;
        }
        
        // check if string is repeat of substring in length 1
        return isSubstringOfLengthK(sArr, 1);
    }
    
    private boolean isSubstringOfLengthK(char[] sArr, int k) {
        for (int i = k; i < sArr.length; i += 1) {
            if (sArr[i] == sArr[i % k])
                continue;
            return false;
        }
        return true;
    }
    
    private List<Integer> getFactor(int num) {
        List<Integer> ans = new ArrayList<>();
        int j = 0, end = (int) Math.sqrt(num);
        for (int i = 2; i <= end; i++) {
            j = num % i;
            if (j == 0) {
                ans.add(i);
                ans.add(num/i);
            }
        }
        return ans;
    }
    
    @Test
    public void test_trueCases() {
        Problem459 sol = new Problem459();
        assertTrue(sol.repeatedSubstringPattern("abab"));
        assertTrue(sol.repeatedSubstringPattern("abcabcabcabc"));
        assertTrue(sol.repeatedSubstringPattern("aaa"));
        assertTrue(sol.repeatedSubstringPattern(""));
    }
    
    @Test
    public void test_falseCases() {
        Problem459 sol = new Problem459();
        assertFalse(sol.repeatedSubstringPattern("aba"));
        assertFalse(sol.repeatedSubstringPattern("a"));
    }
}