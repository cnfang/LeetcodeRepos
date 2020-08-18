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


public class Problem211 {

    class Node{
        private boolean isWord;
        private Node []next;
        Node () {
            isWord = false;
            next = new Node[R];
        }
    }
    
    private Node root;
    private static final int R = 26;
    
    /** Initialize your data structure here. */
    public Problem211() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null)
            return;
        Node head = root;
        for (char c: word.toCharArray()) {
            int idx = c - 'a';
            if (head.next[idx] == null)
                head.next[idx] = new Node();
            head = head.next[idx];
        }
        head.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null) 
            return false;
        return dfs(word, root, 0);
    }
    
    private boolean dfs(String pattern, Node head, int currIdx) {
        if (head == null)
            return false;
        if (currIdx == pattern.length())
            return head.isWord;
        
        char c = pattern.charAt(currIdx);
        if (c == '.') {
            for (int i = 0; i < R; i++) {
                if (dfs(pattern, head.next[i], currIdx+1))
                    return true;
            }
            return false;
        }
        return dfs(pattern, head.next[c - 'a'], currIdx+1);
    }
    

    @ParameterizedTest
    @ValueSource(strings = { "bad", ".ad", "b.." })
    public void test_Case1(String source) {
        Problem211 sol = new Problem211();
        sol.addWord("bad");
        sol.addWord("dad");
        sol.addWord("mad");
        
        assertTrue(sol.search(source));
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "pad" })
    public void test_Case2(String source) {
        Problem211 sol = new Problem211();
        sol.addWord("bad");
        sol.addWord("dad");
        sol.addWord("mad");
        
        assertFalse(sol.search(source));
    }
}