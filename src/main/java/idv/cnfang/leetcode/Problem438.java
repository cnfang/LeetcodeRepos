package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 438> Find All Anagrams in a String

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.


Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".



Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

*/

public class Problem438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>(); 
        
        if (s == null || s.length() < p.length())
            return ans;
        
        char []sArr = s.toCharArray();
        char []pArr = p.toCharArray();
        int []pCount = new int[26];
        int []sCount = new int [26];
        int slen = s.length(), plen = p.length();
        int start = 0;
        
        Arrays.fill(pCount, 0);
        Arrays.fill(sCount, 0);
        for (int i = 0; i < plen; i++) {
            pCount[pArr[i] - 'a'] += 1;
            sCount[sArr[i] - 'a'] += 1;
        }
        
        if (Arrays.equals(pCount, sCount))
            ans.add(0);
        
        for (int end = plen; end < slen; end++) {
            sCount[sArr[start] - 'a'] -= 1;
            sCount[sArr[end] - 'a'] += 1;
            start += 1;
            if (Arrays.equals(pCount, sCount))
                ans.add(start);
        }
        
        return ans;
    }
    
    
    @Test
    public void test1() {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> ans = Arrays.asList(0, 6);
        Problem438 sol = new Problem438();
        assertEquals(ans, sol.findAnagrams(s, p));
    }
    
    @Test
    public void test2() {
        String s = "abab";
        String p = "ab";
        List<Integer> ans = Arrays.asList(0, 1, 2);
        Problem438 sol = new Problem438();
        assertEquals(ans, sol.findAnagrams(s, p));
    }
}