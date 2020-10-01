package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 980> Unique Paths III

On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:
Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)


Example 2:
Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)


Example 3:
Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:
1 <= grid.length * grid[0].length <= 20

====================== Solution ======================
It's classical Hamiltonian path which is NP-complete problem, solving with backtracking
*/


public class Problem980 {
	class Node {
        int row;
        int col;
        Node (int r, int c) {this.row = r; this.col = c;}
    }
    
    private static final int FORBIDDEN = -1;
    private static final int WALKOVER = 0;
    private static final int ORIGIN = 1;
    private static final int DESTINATION = 2;
    private static final int VISITED = 4;
    
    public int uniquePathsIII(int[][] grid) {
        if (grid == null) return 0;
        
        int []ansCnt = {0};
        // inclusive to origin to destination
        int targetVertexCnt = 0;
        Node startNode = null, endNode = null;
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == FORBIDDEN) {
                    continue;
                } else if (grid[row][col] == ORIGIN) {
                    startNode = new Node(row, col);
                } else if (grid[row][col] == DESTINATION) {
                    endNode = new Node(row, col);
                }
                targetVertexCnt += 1;
            }
        }
        
        backtracking(grid, ansCnt, endNode, targetVertexCnt, 0, startNode.row, startNode.col);
        return ansCnt[0];
    }
    
    private void backtracking(int [][]grid, int []ansCnt, Node endNode, 
                     int targetVertexCnt, int previousVertexCnt, int row, int col) {
        
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
            return;
        if (grid[row][col] == VISITED || grid[row][col] == FORBIDDEN)
            return;
        
        int currentVertexCnt = previousVertexCnt + 1;
        if (row == endNode.row && col == endNode.col) {
            ansCnt[0] = ansCnt[0] + (currentVertexCnt == targetVertexCnt? 1: 0);
            return;
        }
        
        int saved = grid[row][col];
        grid[row][col] = VISITED;
        backtracking(grid, ansCnt, endNode, targetVertexCnt, currentVertexCnt, row+1, col);
        backtracking(grid, ansCnt, endNode, targetVertexCnt, currentVertexCnt, row-1, col);
        backtracking(grid, ansCnt, endNode, targetVertexCnt, currentVertexCnt, row, col+1);
        backtracking(grid, ansCnt, endNode, targetVertexCnt, currentVertexCnt, row, col-1);
        grid[row][col] = saved;
        return;
    }
    
    @Test
    public void test() {
        Problem980 sol = new Problem980();
        assertEquals(2, sol.uniquePathsIII(new int [][] {{1,0,0,0},
														 {0,0,0,0},
														 {0,0,2,-1}}));
        
        assertEquals(4, sol.uniquePathsIII(new int [][] {{1,0,0,0},
			 											{0,0,0,0},
			 											{0,0,0,2}}));
        
        assertEquals(0, sol.uniquePathsIII(new int [][] {{0,1},
			 											 {2,0}}));
    }
}