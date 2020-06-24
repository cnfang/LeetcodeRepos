package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 392> Is Subsequence

Given a string s and a string t, check if s is subsequence of t.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

 
 
Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:
0 <= s.length <= 100
0 <= t.length <= 10^4
Both strings consists only of lowercase characters.

====================== Solution ======================
use stack to store prefix
*/

public class Problem392 {
    public boolean isSubsequence(String s, String t) {
        if (t.length() == 0)
            return s.length() == 0? true: false;
        if (s.length() == 0)
            return true;
        
        List<Integer> []book = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            book[i] = new ArrayList<Integer>();
        
        for (int i = 0; i < t.length(); i++)
            book[t.charAt(i) - 'a'].add(i);
        
        return checkExist(book, s, 0, -1);
    }
    
    private boolean checkExist(List<Integer> []book, String s, int index, int threshold) {
        if (index == s.length())
            return true;
        
        List<Integer> mylist = book[s.charAt(index) - 'a'];
        int insertIdx = Math.abs(Collections.binarySearch(mylist, threshold) + 1);
        
        if (mylist.size() == 0 || insertIdx == mylist.size())
            return false;
        return checkExist(book, s, index+1, mylist.get(insertIdx));
    }
    
    @Test
    public void test_example1() {
        String s = "abc";
        String t = "ahbgdc";
        Problem392 sol = new Problem392();
        assertTrue(sol.isSubsequence(s, t));
    }
    
    @Test
    public void test_example2() {
        String s = "axc";
        String t = "ahbgdc";
        Problem392 sol = new Problem392();
        assertFalse(sol.isSubsequence(s, t));
    }
     
}