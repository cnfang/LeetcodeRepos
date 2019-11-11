package idv.cnfang.leetcode;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
Leetcode <Problem 733> Queue & Stack : Flood Fill

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

======================================================
1. DFS
2. BFS
*/


public class Problem733 {
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0] == null) 
            return image;
        
        boolean [][]visited = new boolean[image.length][image[0].length];
        dfs(image, visited, sr, sc, image[sr][sc], newColor);
        
        return image;
    }
  
    private void dfs(int [][] map, boolean [][] visited, int row, int col, int target, int newColor) {
        if (map[row][col] != target || visited[row][col]) return;
        
        visited[row][col] = true;
        map[row][col] = newColor;
        
        if(col + 1 < map[0].length) dfs(map, visited, row, col+1, target, newColor);
        if (row + 1 < map.length) dfs(map, visited, row+1, col, target, newColor);
        if (row - 1 >= 0) dfs(map, visited, row-1, col, target, newColor);
        if (col - 1 >= 0) dfs(map, visited, row, col-1, target, newColor);
        
        return;
    }
    
   
    
    public static void main(String []args) {
      int [][]grid = {{1, 1, 1},
                      {1, 1, 0},
                      {1, 0, 1}};
      
      Problem733 sol = new Problem733();
      long start = System.nanoTime();
      sol.floodFill(grid, 1, 1, 2);
      long end = System.nanoTime();
      System.out.println("DFS Time: " + TimeUnit.NANOSECONDS.toMillis(end-start) + " ms" );
      for (int i = 0; i < grid.length; i++)
      {
          for (int j = 0; j < grid[0].length; j++)
              System.out.print(grid[i][j] + " ");
          System.out.println();
      }
  }
}
