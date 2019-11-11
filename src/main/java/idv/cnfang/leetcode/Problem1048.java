package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 1048> Longest String Chain

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".

Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.

======================================================
DP, using a hashmap to store all words, loop through words array
at each word, delete one character at a time and update the value if number of string chain is bigger than current value
*/

public class Problem1048 {
    
    public int longestStrChain(String[] words) {
        int n = words.length;
        
        // sort the array with string length
        Arrays.sort(words, (a,b) -> a.length()-b.length());
        
        int max = 1;
        // hashmap store the maximum number of string chain
        // key is string, value is maximum number of string chain
        HashMap<String, Integer> dp = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
           dp.put(words[i], 1);
           for (int k = 0; k < words[i].length(); k++) {
               String key = words[i].substring(0, k) + words[i].substring(k+1);
               if (!dp.containsKey(key)) continue;
               int currNumChain = dp.get(key) + 1;
               if (currNumChain > dp.get(words[i])) {
                   dp.put(words[i], currNumChain);
                   max = currNumChain > max? currNumChain: max;
               }
           }
        }
        
        return max;
    }
    
    @Test
    public void test_longestStrChain_Case1() {
        String []words = {"a","b","ba","bca","bda","bdca"};
        Problem1048 sol = new Problem1048();
        int expected = 4;
        assertEquals(expected, sol.longestStrChain(words));
    }
    
    @Test
    public void test_longestStrChain_Case2() {
        String []words = {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        Problem1048 sol = new Problem1048();
        int expected = 7;
        assertEquals(expected, sol.longestStrChain(words));
    }
}


