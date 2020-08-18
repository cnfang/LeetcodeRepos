package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

/**
Leetcode <Problem 79> Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:
board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3

==================== Solution ===================
apply DFS to find the if word exists in the grid.

*/

public class Problem79 {
    private static char markVisited = '*';
    
    public boolean exist(char[][] board, String word) {
        int numRow = board.length, numCol = board[0].length;
        char []wordArr = word.toCharArray();
        
        for (int r = 0; r < numRow; r++)
            for (int c = 0; c < numCol; c++)
                if (board[r][c] == wordArr[0] && dfs(board, wordArr, r, c, 0))
                    return true;
        return false;
    }
    
    private boolean dfs(char [][]board, char[] word, 
                        int r, int c, int idx) {
        if (idx == word.length)
            return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
            return false;
        if (board[r][c] == markVisited) return false;
        
        if (board[r][c] == word[idx]) {
            board[r][c] = markVisited;
            boolean next = dfs(board, word, r+1, c, idx+1) || 
                           dfs(board, word, r-1, c, idx+1) || 
                           dfs(board, word, r, c+1, idx+1) || 
                           dfs(board, word, r, c-1, idx+1);
            board[r][c] = word[idx];
            return next;
        }
        return false;
    }
    
    @Test
    public void test() {
        char [][]board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        Problem79 sol = new Problem79();
        
        assertThat(sol.exist(board, "ABCCED"), is(true));
        assertThat(sol.exist(board, "SEE"), is(true));
        assertThat(sol.exist(board, "ABCB"), is(false));
    }
    
}
