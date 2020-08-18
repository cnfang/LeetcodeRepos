package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 994> Rotting Oranges
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4


Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.


Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.

============= Solution ===========
Queue 
*/


public class Problem994 {
    class Node {
        int r;
        int c;
        Node(int r, int c) {this.r = r; this.c = c;}
    }
    private static int VISITED = 4;
    private static int ROTTED = 2;
    private static int FRESH = 1;
    private static int EMPTY = 0;
    
    public int orangesRotting(int[][] grid) {
        int numRow = grid.length;
        int numCol = grid[0].length;
        int numMin = -1;
        boolean isFreshExist = false;
        
        Queue<Node> queue = new ArrayDeque<>();
        for (int r = 0; r < numRow; r++)
            for (int c = 0; c < numCol; c++) {
                if (grid[r][c] == ROTTED) {
                    queue.add(new Node(r, c));
                    grid[r][c] = VISITED;
                }
                else if (grid[r][c] == FRESH)
                    isFreshExist = true;
            }
        
        // no existence of fresh orange
        if (!isFreshExist)
            return 0;
        // fresh orange exists but no existence of rotted orange
        if (isFreshExist && queue.isEmpty())
            return -1;
        
        // orange started to rot
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                Node node = queue.poll();
                if (node.r - 1 >= 0 && grid[node.r-1][node.c] == FRESH) {
                    queue.add(new Node(node.r-1, node.c));
                    grid[node.r-1][node.c] = VISITED;
                }
                if (node.r + 1 < numRow && grid[node.r+1][node.c] == FRESH) {
                    queue.add(new Node(node.r+1, node.c));
                    grid[node.r+1][node.c] = VISITED;
                }
                if (node.c - 1 >= 0 && grid[node.r][node.c-1] == FRESH) {
                    queue.add(new Node(node.r, node.c-1));
                    grid[node.r][node.c-1] = VISITED;
                }
                if (node.c + 1 < numCol && grid[node.r][node.c+1] == FRESH) {
                    queue.add(new Node(node.r, node.c+1));
                    grid[node.r][node.c+1] = VISITED;
                }
            }
            numMin += 1;
        }
        
        // check existence of fresh orange
        for (int r = 0; r < numRow; r++)
            for (int c = 0; c < numCol; c++)
                if (grid[r][c] == FRESH)
                    return -1;
        return numMin;
    }
     
     
     @Test
     public void test1() {
        int [][]grid = {{2,1,1},{1,1,0},{0,1,1}};
        Problem994 sol = new Problem994();
        assertEquals(4, sol.orangesRotting(grid));
     }
     
     @Test
     public void test2() {
        int [][]grid = {{2,1,1},{0,1,1},{1,0,1}};
        Problem994 sol = new Problem994();
        assertEquals(-1, sol.orangesRotting(grid));
     }
     
     @Test
     public void test3() {
        int [][]grid = {{0,2}};
        Problem994 sol = new Problem994();
        assertEquals(0, sol.orangesRotting(grid));
     }
     
     @Test
     public void test4() {
        int [][]grid = {{2,2},{1,1},{0,0},{2,0}};
        Problem994 sol = new Problem994();
        assertEquals(1, sol.orangesRotting(grid));
     }
     
}