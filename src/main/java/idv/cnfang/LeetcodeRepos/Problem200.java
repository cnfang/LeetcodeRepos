package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
Leetcode <Problem 200> Queue & Stack : Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3

======================================================
1. DFS
2. BFS
*/


public class Problem200 {
    public int numIslands(char[][] grid, String method) {
        if (grid == null || grid.length == 0 || grid[0] == null) 
            return 0;
        
        int numRow = grid.length;
        int numCol = grid[0].length;
        boolean [][] visited = new boolean[numRow][numCol]; 
        int count = 0;
        
        if ("BFS".equals(method))
            for (int row = 0; row < numRow; row++)
                for (int col = 0; col < numCol; col++) {
                    if (grid[row][col] == '0' || visited[row][col]) continue;
                    count++;
                    bfs(grid, visited, row, col);
                }
        else 
            for (int row = 0; row < numRow; row++)
                for (int col = 0; col < numCol; col++) {
                    if (grid[row][col] == '0' || visited[row][col]) continue;
                    count++;
                    dfs(grid, visited, row, col);
                }
        
        return count;
    }
    
    private void dfs(char [][] map, boolean [][]visited, int row, int col) {
        if (map[row][col] == '0' || visited[row][col]) return;
        
        visited[row][col] = true;
        if(col + 1 < map[0].length) dfs(map, visited, row, col+1);
        if (row + 1 < map.length) dfs(map, visited, row+1, col);
        if (row - 1 >= 0) dfs(map, visited, row-1, col);
        if (col - 1 >= 0) dfs(map, visited, row, col-1);
        
        return;
    }
    
    private void bfs(char [][] map, boolean [][]visited, int row, int col) {
        Queue<int[]> waitingQueue = new ArrayDeque<int []>();
        waitingQueue.add(new int[] {row, col});
        
        while (waitingQueue.size() > 0) {
            int []vertex = waitingQueue.poll();
            int r = vertex[0];
            int c = vertex[1];
            visited[r][c] = true;
            if (r + 1 < map.length && map[r+1][c] == '1' && !visited[r+1][c]) waitingQueue.add(new int[] {r+1, c});
            if (c + 1 < map[0].length && map[r][c+1] == '1' && !visited[r][c+1]) waitingQueue.add(new int[] {r, c+1});
            if (r - 1 >= 0 && map[r-1][c] == '1' && !visited[r-1][c]) waitingQueue.add(new int[] {r-1, c});
            if (c - 1 >= 0 && map[r][c-1] == '1' && !visited[r][c-1]) waitingQueue.add(new int[] {r, c-1});
        }
        return;
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"DFS", "BFS"})
    public void testnumIslands(String candidate) {
        char [][]grid = {{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        
        int expected = 1;
        
        Problem200 sol = new Problem200();
        
        //long start = System.nanoTime();
        
        assertEquals(expected, sol.numIslands(grid, candidate));
        
        //long end = System.nanoTime();
        
        //System.out.println(candidate+ " Time: " + TimeUnit.NANOSECONDS.toMillis(end-start) + " ms" );
    }
}