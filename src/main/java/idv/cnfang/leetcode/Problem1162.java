package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1162> As Far from Land as Possible

Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.

 

Example 1:



Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: 
The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:



Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: 
The cell (2, 2) is as far as possible from all the land with distance 4.
 

Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
*/

public class Problem1162 {
    /**
     * maximum distance is calculated through DP, which is faster than bfs
     * @param grid
     * @return
     */
    public int maxDistanceDP(int[][] grid) {
        int numRow = grid.length;
        int numCol = grid[0].length;
        
        int [][]dp = new int[numRow][numCol];
        int maxValue = numRow * numCol;
        
        for (int row = 0; row < numRow; row++)
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 1) {
                    dp[row][col] = 0;
                    continue;
                }
                if (row == 0 && col == 0) dp[row][col] = maxValue;
                else if (row == 0) dp[row][col] = dp[row][col-1] + 1;
                else if (col == 0) dp[row][col] = dp[row-1][col] + 1;
                else dp[row][col] = Math.min(dp[row][col-1], dp[row-1][col])+1;   
            }
        
        for (int row = numRow-1; row >= 0 ; row--)
            for (int col = numCol-1; col >= 0; col--) {
                if (grid[row][col] == 1) continue;
                
                if (row == numRow-1 && col == numCol-1) continue;
                else if (row == numRow-1) dp[row][col] = Math.min(dp[row][col+1]+1, dp[row][col]);
                else if (col == numCol-1) dp[row][col] = Math.min(dp[row+1][col]+1, dp[row][col]);
                else {
                    int tmp = Math.min(dp[row+1][col], dp[row][col+1])+1;
                    dp[row][col] = Math.min(dp[row][col], tmp);
                }
            }
        
        int maxDist = Integer.MIN_VALUE;
        for (int row = 0; row < numRow; row++)
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 1 || dp[row][col] >= maxValue) continue;
                if (dp[row][col] > maxDist) maxDist = dp[row][col];
            }
        
        return maxDist == Integer.MIN_VALUE? -1: maxDist;
    }
    
    
    public int maxDistanceBFS(int[][] grid) {
      int []location = new int[2];
      int maxDist = Integer.MIN_VALUE;
      boolean [][]visited = new boolean[grid.length][grid[0].length];
      
      for (int row = 0; row < grid.length; row++) {
          for (int col = 0; col < grid[0].length; col++) {
              if (grid[row][col] == 1) continue;
              if(bfs(grid, visited, row, col, location) 
                 && dist(row, col, location) > maxDist)
                  maxDist = dist(row, col, location);
          }
      }
      
      return maxDist == Integer.MIN_VALUE? -1: maxDist;
    }
    
    private int dist(int row, int col, int []location) {
      return Math.abs(row-location[0]) + Math.abs(col-location[1]);
    }
    
    private boolean bfs(int [][]grid, boolean [][]visited, int row, int col, int []location) {
      if (row < 0 || row >= grid.length 
          || col < 0 || col >= grid[0].length) return false;
      
      if (visited[row][col]) return false;
      
      if (grid[row][col] == 1) {
          location[0] = row;
          location[1] = col;
          return true;
      };
      
      visited[row][col] = true;
      
      int minDist = Integer.MAX_VALUE;
      int []tmp = new int[2];
      
      if (bfs(grid, visited, row+1, col, location) 
          && dist(row, col, location) < minDist) {
          minDist = dist(row, col, location);
          tmp[0] = location[0];
          tmp[1] = location[1];
      }
      
      if (bfs(grid, visited, row-1, col, location) 
          && dist(row, col, location) < minDist) {
          minDist = dist(row, col, location);
          tmp[0] = location[0];
          tmp[1] = location[1];
      }
      
      if (bfs(grid, visited, row, col+1, location)
         && dist(row, col, location) < minDist) {
          minDist = dist(row, col, location);
          tmp[0] = location[0];
          tmp[1] = location[1];
      }
      
      if (bfs(grid, visited, row, col-1, location) 
         && dist(row, col, location) < minDist) {
          minDist = dist(row, col, location);
          tmp[0] = location[0];
          tmp[1] = location[1];
      }
      
      visited[row][col] = false;
      location[0] = tmp[0];
      location[1] = tmp[1];
      return minDist == Integer.MAX_VALUE? false: true;
    }
    
    /**
     * testing the maxDistance function implemented by BFS
     */
    @Test
    public void testmaxDistanceBFS() {
        Problem1162 sol = new Problem1162();
        int [][]grid = {{1,0,1},{0,0,0},{1,0,1}};
        int expected = 2;
        assertEquals(expected, sol.maxDistanceBFS(grid));
    }
    
    /**
     * testing the maxDistance function implemented by DP, which is faster than BFS
     */
    @Test
    public void testmaxDistanceDP_Case1() {
        Problem1162 sol = new Problem1162();
        int [][]grid = {{1,0,1},{0,0,0},{1,0,1}};
        int expected = 2;
        assertEquals(expected, sol.maxDistanceDP(grid));
    }
    
    @Test
    public void testmaxDistanceDP_Case2() {
        Problem1162 sol = new Problem1162();
      int [][]grid = {{0,0,0,0,1,0,1,0,0,0,1,1,0,1,0,0,1,0,1,0,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1,0,0,0,1,0,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,1,0,1},
      {0,0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,1,0,0,0,1,0,1,1,1,0,1,1,0,0,1,0,0,0,0,1,1,0,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,1,1,0,1,0,1,0},
      {0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,0,1},
      {0,1,1,0,1,1,1,0,0,0,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,1,1,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
      {1,0,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0,0,1,0,1,1,1,1,0,0,1,0,0,0,1,1,1,0,0,1,1,0,1,0,0,0,1,0,1,1,1,0,1,1,1,0,1,1,0,0},
      {0,1,0,0,0,0,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,1,1,0,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,0,1,0,0,1,0,1,1,0,1,0,1,1,1,1,1,1,1},
      {1,0,0,1,1,0,0,1,0,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,1,1,1,0,0,0,0,0,1,1,0,1,1,1,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1,1,1,0,1,0,1,1},
      {0,1,0,1,1,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,0},
      {0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,1,0,0,1,1,0,0,1,0,0,1,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0},
      {1,1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,0,0,1,1,1,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1,0,1},
      {0,0,1,0,0,0,0,0,1,1,1,1,1,0,1,0,1,1,1,0,1,1,0,0,1,1,1,0,0,0,1,0,1,1,1,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
      {1,1,0,0,1,0,0,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,0,0,0,1,1,0,1,1,1,1,0,1,0,0,1,1,0,0,0,1,0,0,1,0,1,1,0,0,0,1,1,0,0},
      {1,0,1,1,0,0,1,0,1,0,1,1,1,1,0,0,0,1,1,0,0,1,1,0,1,0,1,0,1,1,0,1,1,0,0,1,1,0,0,0,1,1,0,1,1,1,1,1,0,1,0,0,1,0,0,0,0,1,0,0},
      {0,0,1,0,1,1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,0,0,1,1,1,0,1,1,1,0,0,0,1,1,0,0,0,1,1,1,0,0,0,1,1,1,1,0,0,0,0},
      {1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,0,0,1,1,0,0,1,1,0,1,0,0},
      {0,1,1,0,0,0,0,1,1,0,0,1,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,0},
      {1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,1,1,0,1,0,1,0,0,0,0,0,0,0,1,0,1},
      {1,1,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,1,1,1,0,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,0,1},
      {0,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1,1,0,0,0,0,0,1,0,1,1,0,1,0,1,1,0,1,1,1,1,0,0},
      {0,1,0,1,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,1,1,1,1,1,0,0,0,0,1,1,0,1,1},
      {0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,1,0,1,1,1,0,1,1,1,0,1,1,0,1,0,1},
      {1,1,1,1,1,1,1,0,0,1,1,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,1,0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,1,1,0,1,0,0,0,0,0},
      {0,0,0,0,0,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,0,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,0,1,1,1,0,0,0,0,0,0,1,1,0,0,0,1},
      {0,1,1,0,0,1,0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0},
      {0,0,0,0,0,1,1,0,0,1,0,1,0,1,0,0,1,1,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1},
      {0,0,0,0,0,1,0,0,1,1,1,0,1,0,1,1,1,0,0,1,0,0,1,0,1,1,0,0,1,1,1,0,0,0,0,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0,1,1,1,0,1,0,0,0,1},
      {0,1,0,0,0,1,1,0,1,0,1,0,1,1,1,1,0,0,1,1,1,0,1,0,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,1,1,0,1,0},
      {1,1,1,1,1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,1,0,1,1,1,0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,0,0,0,1},
      {1,1,0,1,1,0,0,1,0,0,1,1,0,1,0,0,0,0,1,1,1,0,1,1,1,1,0,1,1,0,0,1,0,1,0,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,0,1},
      {1,0,0,1,0,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,0,1},
      {1,1,1,0,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,1,0,0,1,0,1,0,0,0,1,1,1,0,1,1,1,1,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1},
      {1,1,0,1,1,1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,0,0,1,0,0,0,1,1,0,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,0,0,0,1,0,1,0,1},
      {1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,1,1,1,0,1,0,1,0,1,0},
      {1,0,1,0,1,1,0,1,1,0,0,0,0,1,0,1,0,0,1,0,1,0,1,1,0,1,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,1,0,0,1,1,0,0,1,0,0,0,1,1,0,1,0,1},
      {0,1,0,0,1,1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,1,1,0,1,1,1,0,0,0,0,0,0,1,1,0,1,1,0,0,1,1,1,0,0,0,1,0,1,0,0,1,1,1,1,1,1,1,0,0,0},
      {0,0,1,1,1,1,1,0,0,1,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,0,0,1,1,0,1,0,1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1},
      {0,1,1,0,1,0,0,1,0,1,0,1,0,0,1,1,1,0,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,1,1,1,0,0,1},
      {1,1,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,1,1,1,0,1,1,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,0,0,1,1,0},
      {1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,0,1,0,0},
      {0,1,0,0,0,0,0,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,1,0,1,0,1,1,0,0,1,0,1,1},
      {1,1,1,1,1,0,0,1,0,0,1,0,1,1,0,1,1,0,0,0,0,1,1,1,1,0,0,1,1,0,0,0,1,0,1,0,1,1,1,1,1,0,1,1,0,0,1,1,0,0,0,1,0,0,1,0,0,0,0,1},
      {1,1,0,1,0,1,1,0,0,0,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,0,0,1,1,1,0,1,0},
      {0,0,0,0,1,1,0,1,0,0,0,1,0,0,1,1,1,0,1,0,0,0,0,1,1,1,0,0,1,1,1,0,1,1,0,0,0,0,0,0,1,0,1,1,0,0,0,0,1,1,0,0,0,1,0,1,0,1,1,0},
      {0,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,1,0,0,0,1,0,0,1,0,1,0,1,1,1,1,1,1,0,1,1,0,0,0,1,1,1,0,0,1,1,0,0,1,1,1,0,0,1},
      {1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,1,1,0,0,1,1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,0,1,1,1},
      {1,1,1,0,0,1,1,0,0,0,1,1,0,1,1,0,0,1,0,1,1,1,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1},
      {1,1,1,0,0,0,0,1,0,1,0,1,1,0,0,1,1,1,0,0,1,1,0,0,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,0,1,1,1,0,1,1,1,0,0,1,0,1,1},
      {1,1,1,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,0,0,0,1,1,0,1,0,0,1,0,0,1,1,1,0,1,0,1,1,1,1,1,1,0,0,1,1,1},
      {1,0,1,0,0,0,0,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,1,1,1,1,0,1,1},
      {1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,0,0,1,1,0,1,0,0,0,0,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,0,1,0,1,1,1,0,0,1,1,0,0},
      {0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,0,0,1,1,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,1,0,1,0,0,0,1,1,1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,0},
      {0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,0,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,1,1,1,0,0,0,1,1,0,0,0,1,0},
      {0,0,1,0,1,0,0,1,0,0,0,1,0,1,0,0,1,1,0,1,1,0,1,0,1,0,0,1,1,1,0,1,0,0,0,1,0,0,0,0,0,1,1,1,1,0,1,1,0,0,0,0,1,0,0,0,0,0,0,1},
      {0,1,0,1,0,0,0,1,0,1,1,0,1,0,0,1,0,0,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,0,0,0,1,1,0,1,1,0,0,0,0,1,1,1,1,0,1,0,0,1,0,1,0,0,0,1},
      {1,1,1,0,1,1,0,0,0,1,1,1,0,1,0,0,1,1,1,0,0,1,0,0,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,0,1,1,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,0,0},
      {1,1,1,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,1,0,1,1,1,1,0,0,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,0,0,0,0,1,0,0,1,1,1,0,1,0,1,0},
      {0,0,1,1,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,1,1,1,0,0,0,0,1,1,0,1,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,0,0,1},
      {0,0,0,0,1,1,1,1,0,0,1,1,1,0,0,1,1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,1,1,0,1,1,0,1,1,0,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,0},
      {0,0,0,1,0,1,0,1,1,1,0,1,1,0,0,0,1,1,0,0,1,0,0,0,1,0,0,1,1,0,0,0,0,1,0,1,1,0,1,0,0,1,1,0,0,0,1,1,1,0,1,0,0,1,0,0,1,1,1,0},
      {1,0,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,1,1,1,0,0,0,1,1,0,1,1,0,1,0,1,1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0}};
        int expected = 4;
        assertEquals(expected, sol.maxDistanceDP(grid));
    }
    
}