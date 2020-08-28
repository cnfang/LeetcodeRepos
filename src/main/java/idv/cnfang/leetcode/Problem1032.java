package idv.cnfang.leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
Leetcode <Problem 1032> Stream of Characters

Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.


Example:
StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 

Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
======================= Solution ===================
use Trie to build the word dictionary, traverse the Trie with from root and from the nodes that are in the queue
*/

public class Problem1032 {
    
    class TreeNode {
        private static final int SIZE = 26;
        boolean isWord;
        TreeNode []next;
        TreeNode() {isWord = false; next = new TreeNode[SIZE];}
    }
    
    private void addWordToTree(String word) {
        TreeNode head = root;
        int idx;
        for (char c: word.toCharArray()) {
            idx = c - 'a';
            if (head.next[idx] == null)
                head.next[idx] = new TreeNode();
            head = head.next[idx];
        }
        head.isWord = true;
        return;
    }
    
    private TreeNode root;
    private Queue<TreeNode> queue;
    
    public Problem1032(String[] words) {
        root = new TreeNode();
        queue = new ArrayDeque<>();
        
        for (String word: words) 
            addWordToTree(word);
    }
    
    public boolean query(char letter) {
        boolean isExist = false;
        int qSize = queue.size();
        int idx = letter - 'a';
        TreeNode node;
        
        // check word of size larger than 1
        for (int i = 0; i < qSize; i++) {
            node = queue.poll();
            node = node.next[idx];
            if (node == null)
                continue;

            isExist = node.isWord? true: isExist;
            queue.add(node);
        }
        
        // check word of size equal to 1
        node = root;
        node = node.next[idx];
        if (node != null) {
            isExist = node.isWord? true: isExist;
            queue.add(node);
        }
        
        return isExist;
    }
    
    
    public static void main(String []args) {
        String[] words = {"cd","f","kl"};
        Problem1032 sol = new Problem1032(words);
        assertFalse(sol.query('a'));
        assertFalse(sol.query('b'));
        assertFalse(sol.query('c'));
        assertTrue(sol.query('d'));
        assertFalse(sol.query('e'));
        assertTrue(sol.query('f'));
        assertFalse(sol.query('g'));
        assertFalse(sol.query('h'));
        assertFalse(sol.query('i'));
        assertFalse(sol.query('j'));
        assertFalse(sol.query('k'));
        assertTrue(sol.query('l'));
    }
}


