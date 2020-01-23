package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 130> Surrounded Regions

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are 
connected if they are adjacent cells connected horizontally or vertically.

================ Solution ================
Step 1. bfs on border first, for those area containing border 'O', keep this area unchange
Step 2. bfs on non-border area, and flip 'O' to 'X'
*/

public class Problem130 {
    
    public void solve(char[][] board) {
        if (board == null || board.length < 1)
            return;
        
        int row = board.length, col = board[0].length;
        boolean [][]visited = new boolean[row][col];
        
        // first and last row
        for (int c = 0; c < col; c++) {
            bfs(board, visited, 0, c, false);
            bfs(board, visited, row-1, c, false);
        }
        
        // first and last col
        for (int r = 0; r < row; r++) {
            bfs(board, visited, r, 0, false);
            bfs(board, visited, r, col-1, false);
        }
        
        for (int r = 1; r < row-1; r++)
            for (int c = 1; c < col-1; c++)
                bfs(board, visited, r, c, true);
        
        return;
        
    }
    
    private void bfs(char [][]board, boolean [][]visited, int r, int c, boolean flip) {
        int row = board.length, col = board[0].length;
        
        // base base
        if (r == -1 || r == row || c == col || c == -1)
            return;
        
        if (visited[r][c])
            return;
        
        visited[r][c] = true;
        
        if (board[r][c] == 'X')
            return;
        
        if (flip)
            board[r][c] = 'X';
        
        bfs(board, visited, r, c+1, flip);
        bfs(board, visited, r, c-1, flip);
        bfs(board, visited, r+1, c, flip);
        bfs(board, visited, r-1, c, flip);
        
        return;
    }
    
    @Test
    public void example1() {
        Problem130 sol = new Problem130();
        char[][] expected = {{'X','X','X','X'},
                             {'X','X','X','X'},
                             {'X','X','X','X'},
                             {'X','O','X','X'}};
        char[][] board = {{'X','X','X','X'},
                          {'X','O','O','X'},
                          {'X','X','O','X'},
                          {'X','O','X','X'}};
        sol.solve(board);
        assertThat(board, equalTo(expected));
    }
    
    @Test
    public void example2() {
        Problem130 sol = new Problem130();
        char[][] expected = {{'O','O','O'},
                             {'O','O','O'},
                             {'O','O','O'}};
        char[][] board = {{'O','O','O'},
                          {'O','O','O'},
                          {'O','O','O'}};
        sol.solve(board);
        assertThat(board, equalTo(expected));
    }
}
