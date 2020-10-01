package idv.cnfang.leetcode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
Leetcode <Problem 139> Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

============== Solution =============
1. using Trie to store wordDict and boolean array to remember falseAttemp
2. using hashset to store wordDict and boolean array to remember falseAttemp
*/


public class Problem139 {
    
    public boolean wordBreak(String s, List<String> wordDict) {
    	Set<String> prefixSet = new HashSet<>(wordDict);
    	boolean []falseAttempAtIndex = new boolean [s.length()];
    	Arrays.fill(falseAttempAtIndex, true);
    	
    	return inner(falseAttempAtIndex, s, prefixSet, 0);
    }
    
    private boolean inner(boolean []falseAttempAtIndex, String s, Set<String> prefixSet, int startIdx) {
    	if (startIdx == s.length())
    		return true;
    	if (!falseAttempAtIndex[startIdx])
    		return false;
    	
    	for (int last = startIdx + 1; last <= s.length(); last++) {
    		if (!prefixSet.contains(s.substring(startIdx, last)))
    			continue;
    		if (inner(falseAttempAtIndex, s, prefixSet, last))
    			return true;
    	}
    	falseAttempAtIndex[startIdx] = false;
    	return false;
    }
    
    @Test
    public void test() {
    	Problem139 sol = new Problem139();
    	assertTrue(sol.wordBreak("leetcode", Arrays.asList("leet", "code")));
    	assertTrue(sol.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    	assertFalse(sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    	assertTrue(sol.wordBreak("catsandogcat", Arrays.asList("cats","dog","sand","and","cat","an")));
    	assertFalse(sol.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    	assertFalse(sol.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba")));
    }
    
}