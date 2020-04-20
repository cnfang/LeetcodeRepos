package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.PriorityQueue;

/**
Leetcode <Problem 64> Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

================ Solution =============
1. Dijkstra's algorithm
2. DP

*/

public class Problem64 {
    private class Rank implements Comparable<Rank>{
        public int row, col, rank;
        
        public Rank(int row, int col, int rank) {
            this.row = row;
            this.col = col;
            this.rank = rank;
        }
        
        @Override
        public int compareTo(Rank o) {
            if (this.rank < o.rank)
                return -1;
            else if (this.rank > o.rank)
                return 1;
            return 0;
        }
        
    }
    
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        
        // method1: dijkstra
        // return dijkstra(grid);
        
        // method2: DP
        return dp(grid);
        
    }
    
    private int dp(int [][]grid) {
        int numRow = grid.length, numCol = grid[0].length;
        int [][]distTo = new int [numRow][numCol];
        
        // initialization
        for (int r = 0; r < numRow; r++)
            distTo[r] = new int[numCol];
        
        
        for (int r = 0; r < numRow; r++)
            for (int c = 0; c < numCol; c++) {
                if (r == 0)
                    distTo[r][c] = c > 0? distTo[r][c-1] + grid[r][c]: grid[r][c];
                else if (c == 0)
                    distTo[r][c] = r > 0? distTo[r-1][c] + grid[r][c]: grid[r][c];
                else
                    distTo[r][c] = Math.min(distTo[r-1][c]+grid[r][c], distTo[r][c-1]+grid[r][c]);
            }
        
        return distTo[numRow-1][numCol-1];
    }
    
    private int dijkstra(int [][]grid) {
        int numRow = grid.length, numCol = grid[0].length;
        Rank [][]rankM = new Rank[numRow][numCol];
        
        // initialize distance to all vertices
        for (int r = 0; r < numRow; r++) {
            rankM[r] = new Rank[numCol];
            for (int c = 0; c < numCol; c++) {
                rankM[r][c] = new Rank(r, c, Integer.MAX_VALUE);
            }
        }
        rankM[0][0].rank = grid[0][0];
        
        // start to find minimized path to all vertex
        PriorityQueue<Rank> pq = new PriorityQueue<>();
        pq.add(rankM[0][0]);
        
        while (!pq.isEmpty()) {
            Rank vertexRank = pq.poll();
            // check neighboring vertex (right)
            relax(grid, rankM, pq, vertexRank, vertexRank.row, vertexRank.col+1);
            // check neighboring vertex (down)
            relax(grid, rankM, pq, vertexRank, vertexRank.row+1, vertexRank.col);
        } 
        
        // return the distance to bottom right vertice
        return rankM[numRow-1][numCol-1].rank;
    }
    
    
    private void relax(int [][]grid, Rank [][]rankM, PriorityQueue<Rank> pq, Rank from, int toRow, int toCol) {
        if (toRow >= grid.length || toCol >= grid[0].length)
            return;
        
        if (rankM[toRow][toCol].rank > rankM[from.row][from.col].rank + grid[toRow][toCol]) {
            rankM[toRow][toCol].rank = rankM[from.row][from.col].rank + grid[toRow][toCol];
 
            if (!pq.contains(rankM[toRow][toCol]))
                pq.add(rankM[toRow][toCol]);
        }
        return;
    }
    
    @Test
    public void test_example1() {
        int [][]grid = {{1,3,1},
                        {1,5,1},
                        {4,2,1}};
        
        int expected = 7;
        Problem64 sol = new Problem64();
        assertThat(expected, equalTo(sol.minPathSum(grid)));
    }
    
}
