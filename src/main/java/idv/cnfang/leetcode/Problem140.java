package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
Leetcode <Problem 140> Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.


Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]


Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.


Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

================== Solution =================
1. Using DFS to constantly check if wordDict has prefix of string s, then build a sentence from it <Solved but not elegant enough>
2. solution from leetcode sharing @Cheng_Zhang

*/


public class Problem140 {
    class TrieNode {
        TrieNode []child;
        String word;
        TrieNode() {
            child = new TrieNode[26];
            word = null;
        }
    }
    
    private void addWord(TrieNode root, String s) {
        for (char c: s.toCharArray()) {
            int idx = c - 'a';
            if (root.child[idx] == null)
                root.child[idx] = new TrieNode();
            root = root.child[idx];
        }
        root.word = s;
        return;
    }
    
    /*
     * given a string s, find as much as word existing in the root
     * */
    private void findWord(List<String> candidates, TrieNode root, String s, int currIdx) {
        if (root == null || currIdx == s.length()) 
            return;
        
        TrieNode next = root.child[s.charAt(currIdx) - 'a'];
        
        if (next == null)
            return;
        
        if (next.word != null)
            candidates.add(next.word);
        
        findWord(candidates, next, s, currIdx + 1);
        
        return;
    }
    
    
    private void buildSentence(List<String> []wordCandidate, String s, List<String> ans, StringBuilder sentence, int start) {
        if (start >= s.length()) {
            ans.add(sentence.toString().substring(0, sentence.length()-1));
            return;
        }
        
        for (String candidate: wordCandidate[start]) {
            sentence.append(candidate + " ");
            buildSentence(wordCandidate, s, ans, sentence, start + candidate.length());
            sentence.delete(sentence.length()-candidate.length()-1, sentence.length());
        }
        return;
    }
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0 || s == null || s.length() == 0)
             return Arrays.asList();
         
         // edge case: some characters in target string doesn't exist in wordDict
         Set<Character> targetSet = new HashSet<>();
         Set<Character> sourceSet = new HashSet<>();
         for (char c: s.toCharArray()) targetSet.add(c);
         for (String str: wordDict) {
             for (char c: str.toCharArray())
                 sourceSet.add(c);
         }
         if (!sourceSet.containsAll(targetSet)) return  Arrays.asList();
         
         
         // add word into Trie
         TrieNode root = new TrieNode();
         for (String word: wordDict)
             addWord(root, word);
         
         
         // wordCadidate[i] collects words that started with s[i:end]
         List<String> []wordCandidate = new List[s.length()];
         for (int i = 0; i < s.length(); i++) {
             wordCandidate[i] = new ArrayList<>();
             findWord(wordCandidate[i], root, s.substring(i), 0);
         }
         
         List<String> ans = new ArrayList<>();
         StringBuilder sentence = new StringBuilder();
         
         // it's like brick jumping that you change starting position everytime
         buildSentence(wordCandidate, s, ans, sentence, 0);
         return ans;
     }
    
    @Test
    public void test1() {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> expected = Arrays.asList("cats and dog", "cat sand dog");
        
        Problem140 sol = new Problem140();
        assertThat(sol.wordBreak(s, wordDict))
            .containsExactlyInAnyOrderElementsOf(expected);
    }
    
    @Test
    public void test2() {
        String s = "pineapplepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        List<String> expected = Arrays.asList("pine apple pen apple", "pineapple pen apple", "pine applepen apple");
        
        Problem140 sol = new Problem140();
        
        assertThat(sol.wordBreak(s, wordDict))
            .containsExactlyInAnyOrderElementsOf(expected);
    }
    
    @Test
    public void test3() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        List<String> expected = Arrays.asList();
        
        Problem140 sol = new Problem140();
        
        assertThat(sol.wordBreak(s, wordDict))
            .containsExactlyInAnyOrderElementsOf(expected);
    }
    
    @Test
    public void test4() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        List<String> expected = Arrays.asList();
        
        Problem140 sol = new Problem140();
        
        assertThat(sol.wordBreak(s, wordDict))
            .containsExactlyInAnyOrderElementsOf(expected);
    }
}

//public List<String> wordBreak(String s, Set<String> wordDict) {
//    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
//}       
//
//// DFS function returns an array including all substrings derived from s.
//List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
//    if (map.containsKey(s)) 
//        return map.get(s);
//        
//    LinkedList<String>res = new LinkedList<String>();     
//    if (s.length() == 0) {
//        res.add("");
//        return res;
//    }               
//    for (String word : wordDict) {
//        if (s.startsWith(word)) {
//            List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
//            for (String sub : sublist) 
//                res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
//        }
//    }       
//    map.put(s, res);
//    return res;
//}