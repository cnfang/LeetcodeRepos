package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 212> Trie: Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.

==============================================
1. build a trie with given words
2. look through board to set the start point of trie
3. with start point of trie, use backtracking and dfs to further traversal the trie


*/

public class Problem212 {
    
    class Node {
        Node []next;
        String word;
        public Node() {next = new Node[26];}
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        Node root = buildTrie(words);
        
        Set<String> sol = new HashSet<String>();
        boolean [][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[0].length; col++)
                dfs(sol, root, board, visited, row, col);
        
        return new ArrayList<String>(sol);
    }
    
    private void dfs(Set<String> sol, Node currNode, char [][]map, boolean [][]visited, int row, int col) {
        if (visited[row][col]) return;
        
        char ch = map[row][col];
        int idx = ch - 'a';
        currNode = currNode.next[idx];
        
        if (currNode != null && currNode.word != null) sol.add(currNode.word);
        if (currNode == null) return;
        
        visited[row][col] = true;
        
        if (row+1 < map.length) dfs(sol, currNode, map, visited, row+1, col);
        if (row-1 >= 0) dfs(sol, currNode, map, visited, row-1, col);
        if (col+1 < map[0].length) dfs(sol, currNode, map, visited, row, col+1);
        if (col-1 >= 0) dfs(sol, currNode, map, visited, row, col-1);
        
        visited[row][col] = false;
        return;
    }
    
    private Node buildTrie(String[] words) {
        Node root = new Node();
        
        for (String word: words) {
            Node curr = root;
            for (char ch: word.toCharArray()) {
                int idx = ch - 'a';
                if (curr.next[idx] == null) curr.next[idx] = new Node();
                curr = curr.next[idx];
            }
            curr.word = word;
        }
        
        return root;
    }
   
    @Test
    public void test_Case1() {
        char [][]board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
               };
        
        String []words = {"oath","pea","eat","rain"};
        Problem212 sol = new Problem212();
        List<String> expected = Arrays.asList("oath","eat");
        assertEquals(expected, sol.findWords(board, words));
    }
    
    @Test
    public void test_Case2() {
        char [][]board = {{'a'}};
        String []words = {"a"};
        
        Problem212 sol = new Problem212();
        List<String> expected = Arrays.asList("a");
        assertEquals(expected, sol.findWords(board, words));
    }
    
    @Test
    public void test_Case3() {
        char [][]board = {{'a'}, {'a'}};
        String []words = {"a"};
        
        Problem212 sol = new Problem212();
        List<String> expected = Arrays.asList("a");
        assertEquals(expected, sol.findWords(board, words));
    }
}
