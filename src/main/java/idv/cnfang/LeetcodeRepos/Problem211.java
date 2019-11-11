package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
/**
  Leetcode <Problem 211> Trie: Add and Search Word - Data structure design
  Design a data structure that supports the following two operations:

  void addWord(word)
  bool search(word)
  search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

  Example:

  addWord("bad")
  addWord("dad")
  addWord("mad")
  search("pad") -> false
  search("bad") -> true
  search(".ad") -> true
  search("b..") -> true
  Note: You may assume that all words are consist of lowercase letters a-z.
*/


class Trie {
    private Node root;
    private static final int R = 26;
    
    private static class Node{
        private char val;
        private Node []next = new Node[R];
    }
    
    /** constructor for initializing Trie object*/
    public Trie() {}
    
    /** add word to Trie*/
    public boolean put(String word) {
        if (word == null) return false;
        root = put(root, word, '#', 0);
        return true;
    }
    
    private Node put(Node x, String word, char val, int d) {
        if (x == null) x = new Node();
        if (d == word.length()) {
            x.val = val;
            return x;
        }
        char c = word.charAt(d);
        x.next[c-'a'] = put(x.next[c-'a'], word, val, d+1);
        return x;
    }
    
    public Iterable<String> keysThatMatch(String pattern) {
        LinkedList<String> results = new LinkedList<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
       
    }
    
    private void collect(Node x, StringBuilder prefix, String pattern, LinkedList<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val == '#')
            results.add(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append((char)(ch+'a'));
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c-'a'], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static void main(String []args) {
    }
}

public class Problem211 {

    private Trie trie;
    /** Initialize your data structure here. */
    public Problem211() {
        this.trie = new Trie();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        this.trie.put(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        LinkedList<String> results = (LinkedList<String>) this.trie.keysThatMatch(word);
        if (results.size() == 0) return false;
        return true;
    }

    public static void main(String []args) {
        Problem211 wordDict = new Problem211();
        wordDict.addWord("bad");
        wordDict.addWord("dad");
        wordDict.addWord("mad");
        System.out.println(wordDict.search("pad"));
        System.out.println(wordDict.search("ba"));
        System.out.println(wordDict.search(".acs"));
        System.out.println(wordDict.search("b.."));
    }
    

    @ParameterizedTest
    @ValueSource(strings = { "bad", ".ad", "b.." })
    public void test_Case1(String source) {
        Problem211 wordDict = new Problem211();
        wordDict.addWord("bad");
        wordDict.addWord("dad");
        wordDict.addWord("mad");
        
        assertTrue(wordDict.search(source));
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "pad" })
    public void test_Case2(String source) {
        Problem211 wordDict = new Problem211();
        wordDict.addWord("bad");
        wordDict.addWord("dad");
        wordDict.addWord("mad");
        
        assertFalse(wordDict.search(source));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

//class TrieNode {
//    TrieNode[] children;
//    boolean wordFound;
//    public TrieNode(){
//        children = new TrieNode[26];
//        wordFound = false;
//    }
//    
//}
//class WordDictionary {
//    private TrieNode root;
//
//    /** Initialize your data structure here. */
//    public WordDictionary() {
//        root = new TrieNode();
//    }
//    
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        TrieNode node = root;
//        for (int i = 0; i < word.length(); i++) {
//            int j = word.charAt(i) - 'a';
//            if (node.children[j] == null) {
//                node.children[j] = new TrieNode();
//            }
//            node = node.children[j];
//        }
//        node.wordFound = true;
//        
//    }
//    
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        return find(word,root,0);
//    }
//    private boolean find(String word, TrieNode node, int index) {
//        if (index == word.length()) {
//            return node.wordFound;
//        }
//        if (word.charAt(index) == '.') {
//            for (TrieNode temp: node.children) {
//                if (temp != null && find(word,temp,index+1)) {
//                    return true;
//                }
//            }
//            return false;
//        } else {//不是点是字母
//            int j = word.charAt(index) - 'a';
//            TrieNode temp = node.children[j];
//            return temp != null && find(word,temp,index+1);
//            
//        }
//    }
//}