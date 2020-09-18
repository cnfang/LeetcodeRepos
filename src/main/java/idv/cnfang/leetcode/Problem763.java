package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;
/**
Leetcode <Problem 763> Partition Labels

A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 

Note:
S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.

====================== Solution ======================

*/

public class Problem763 {
	public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0)
            return Arrays.asList();
        
        char []strArr = S.toCharArray();
        int []lastIdxArr = new int[26];
        Character []alphaArr = new Character[26];
        
        for (char i = 0; i < 26; i++) {
            alphaArr[i] = (char)(i + 'a');
            lastIdxArr[i] = -1;
        }
        for (int i = 0; i < strArr.length; i++)
            lastIdxArr[strArr[i] - 'a'] = i;
        
       
        // sort nodeArr using lastIdx
        Arrays.sort(alphaArr, (ch1, ch2) -> Integer.compare(lastIdxArr[ch1-'a'], lastIdxArr[ch2-'a']));
        
        int alphaIdx = 0;
        while (alphaIdx < 26 && lastIdxArr[alphaArr[alphaIdx] - 'a'] == -1) alphaIdx += 1;
        
        List<Integer> ans = new ArrayList<>();
        separateString(ans, strArr, 0, alphaArr, alphaIdx, lastIdxArr);
        return ans;
    }
    
    private void separateString(List<Integer> ans, char []strArr, int startIdx, 
                                Character []alphaArr, int alphaIdx,
                                int []lastIdxArr) {
        if (alphaIdx == 26 || startIdx == strArr.length)
            return;
        
        int endIdx = lastIdxArr[alphaArr[alphaIdx] - 'a'];
        char endChar = alphaArr[alphaIdx];
        for (int i = startIdx; i <= endIdx; i++) {
            int idx = strArr[i] - 'a';
            if (lastIdxArr[idx] > endIdx) {
                endIdx = lastIdxArr[idx];
                endChar = strArr[i];
            }
        }
        
        while (!alphaArr[alphaIdx].equals(endChar))
        	alphaIdx += 1;
        
        ans.add(endIdx - startIdx + 1);
        separateString(ans, strArr, endIdx+1, alphaArr, alphaIdx+1, lastIdxArr);
        return;
    }
    
    @Test
    public void test1() {
    	Problem763 sol = new Problem763();
    	assertEquals(Arrays.asList(9,7,8), sol.partitionLabels("ababcbacadefegdehijhklij"));
    }
    
    @Test
    public void test2() {
    	Problem763 sol = new Problem763();
    	assertEquals(Arrays.asList(9,1), sol.partitionLabels("aebbedaddc"));
    }
}