package idv.cnfang.leetcode;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 290> Word Pattern

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:
Input: pattern = "abba", str = "dog cat cat dog"
Output: true


Example 2:
Input:pattern = "abba", str = "dog cat cat fish"
Output: false


Example 3:
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false


Example 4:
Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:

You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
================= Solution =================

*/

public class Problem290 {
	public boolean wordPattern(String pattern, String str) {
        if (str == null && pattern == null)
            return true;
        if (str == null || pattern == null)
            return false;
        if (str.length() == 0 && pattern.length() == 0)
            return true;
        if (str.length() == 0 || pattern.length() == 0)
            return false;
        
        char []parr = pattern.toCharArray();
        String []sarr = str.split(" ");
        
        if (parr.length != sarr.length)
        	return false;
        
        Map<Character, String> alphabet2Word = new HashMap<>();
        Map<String, Character> word2alphabet = new HashMap<>();
        
        for (int i = 0; i < sarr.length; i++) {
        	if (alphabet2Word.get(parr[i]) == null && 
        		word2alphabet.get(sarr[i]) == null) {
        		alphabet2Word.put(parr[i], sarr[i]);
        		word2alphabet.put(sarr[i], parr[i]);
        		continue;
        	}
        	if (alphabet2Word.get(parr[i]) != null && alphabet2Word.get(parr[i]).equals(sarr[i]) &&
        		word2alphabet.get(sarr[i]) != null && word2alphabet.get(sarr[i]).equals(parr[i]))
        		continue;
        	
        	return false;
        }
        return true;
    }
    
    @Test
    public void trueCases() {
        Problem290 sol = new Problem290();
        assertThat(sol.wordPattern("abba", "dog cat cat dog"), is(true));
        
    }
    
    @Test
    public void falseCases() {
    	Problem290 sol = new Problem290();
        assertThat(sol.wordPattern("abba", "dog cat cat fish"), is(false));
        assertThat(sol.wordPattern("aaaa", "dog cat cat dog"), is(false));
        assertThat(sol.wordPattern("abba", "dog dog dog dog"), is(false));
        assertThat(sol.wordPattern("aba", "dog cat cat"), is(false));
    }
}
