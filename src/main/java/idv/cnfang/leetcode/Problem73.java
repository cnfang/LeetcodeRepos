package idv.cnfang.leetcode;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 73> Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]


Example 2:
Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

*/

public class Problem73 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return;
        
        int rowNum = matrix.length, colNum = matrix[0].length;
        boolean []rowVisit = new boolean[rowNum];
        boolean []colVisit = new boolean[colNum];
        
        for (int r = 0; r < rowNum; r++) {
            for (int c = 0; c < colNum; c++) {
                if (matrix[r][c] == 0) {
                    rowVisit[r] = true;
                    colVisit[c] = true;
                }
            }
        }
        
        for (int r = 0; r < rowNum; r++) {
            for (int c = 0; c < colNum; c++) {
                matrix[r][c] = rowVisit[r]? 0: colVisit[c]? 0: matrix[r][c];
            }
        }
    }
    
    @Test
    public void example1() {
        int [][]matrix = {{1,1,1},{1,0,1},{1,1,1}};
        int [][]expected = {{1,0,1},{0,0,0},{1,0,1}};
        Problem73 sol = new Problem73();
        sol.setZeroes(matrix);
        assertThat(matrix, equalTo(expected));
    }
    
    @Test
    public void example2() {
        int [][]matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        int [][]expected = {{0,0,0,0},{0,0,0,4},{0,0,0,0},{0,0,0,3},{0,0,0,0}};
        Problem73 sol = new Problem73();
        sol.setZeroes(matrix);
        assertThat(matrix, equalTo(expected));
    }
}
