package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 193> Hash Table: Valid Sudoku

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/

public class Problem193 {
    public boolean isValidSudoku(char[][] board) {
        return check(board, 0, 0);
    }
    
    public boolean isValidSudoku(String[][] board) {
        return check(board, 0, 0);
    }
    
    private boolean check(char [][]map, int row, int col) {
        if (row == 10) return true;
        if (col == 9) return check(map, row+1, 0);
        if (map[row][col] == '.') return check(map, row, col+1);
        
        int rowQ = row/3;
        int colQ = col/3;
        for (int i = 0; i < 9; i++) {
            if (i != row && map[i][col] == map[row][col]) return false; 
            if (i != col && map[row][i] == map[row][col]) return false;
            int g = rowQ*3 + i/3;
            int h = colQ*3 + i%3;
            if (g != row && h != col && map[g][h] == map[row][col]) return false;
        }
        return check(map, row, col+1);
    }
    
    private boolean check(String [][]map, int row, int col) {
        if (row == 9) return true;
        if (col == 9) return check(map, row+1, 0);
        if (map[row][col] == ".") return check(map, row, col+1);
        
        int rowQ = row/3;
        int colQ = col/3;
        for (int i = 0; i < 9; i++) {
            if (i != row && map[i][col] == map[row][col]) return false; 
            if (i != col && map[row][i] == map[row][col]) return false;
            int g = rowQ*3 + i/3;
            int h = colQ*3 + i%3;
            if (g != row && h != col && map[g][h] == map[row][col]) return false;
        }
        return check(map, row, col+1);
    }
    
    
    @Test
    public void testisValidSudoku_Case1() {
        String [][]map = {
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
              };
        Problem193 sol = new Problem193();
        boolean result = sol.isValidSudoku(map);
        assertTrue(result);
    }
    
    @Test
    public void testisValidSudoku_Case2() {
        String [][]map = {
                {"8","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
              };
        Problem193 sol = new Problem193();
        boolean result = sol.isValidSudoku(map);
        assertFalse(result);
    }
}