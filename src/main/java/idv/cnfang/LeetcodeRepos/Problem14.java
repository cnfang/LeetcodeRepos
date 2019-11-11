package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 14> Array & String: Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

=============================================
1. set prefix as first element of array, loop through whole array and reduce the length of prefix if 
element doesn't start with current prefix 

*/

public class Problem14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        else if (strs.length == 1) return strs[0];
        
        String prefix = strs[0];
        int lastIndex = prefix.length();
        
        for (int i = 1; i < strs.length; i++) {
            while (lastIndex >= 0 && ! strs[i].startsWith(prefix.substring(0, lastIndex)))
                    lastIndex--;
        }
        
        return prefix.substring(0, lastIndex);
    }
    
    @Test
    public void testlongestCommonPrefix() {
        String []strs = new String [] {"flower","flow","flight"};
        Problem14 sol = new Problem14();
        //System.out.println(sol.longestCommonPrefix(strs));
        String expected = "fl";
        assertEquals(expected, sol.longestCommonPrefix(strs));
    }
}