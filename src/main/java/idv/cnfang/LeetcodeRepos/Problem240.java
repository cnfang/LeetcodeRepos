package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
Leetcode <Problem 240> Recursion II : Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

====================== Solution ======================
apply divide and conquer technique, 
If our target is equal to the pivot, we have found our target and immediately return the result.
If our target is less than the pivot, we can discard the bottom-right sub-matrix. All elements in that region must be greater or equal than the pivot.
If our target is greater than the pivot, we can discard the top-left sub-matrix. All elements in that region must be less than or equal than the pivot.
*/

public class Problem240 {
    public boolean searchMatrix(int[][] matrix, int target) {
       if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
       return helper(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
    }
    
    private boolean helper(int [][]matrix, int target, int rowStart, int rowEnd, int colStart, int colEnd) {
        // base case
        if (rowStart > rowEnd || colStart > colEnd) return false;
       
        // search for suitable column to put down
        int col = (colStart + colEnd) >> 1;
        if (target == matrix[rowStart][col] || target == matrix[rowEnd][col]) return true;
        while (col > colStart && target < matrix[rowStart][col]) col -= 1;
        while (col < colEnd && target > matrix[rowEnd][col]) col += 1;
        if (col < colStart || col > colEnd) return false;
        
        // search for row to contain discarded area and next searching area
        int row = rowStart;
        while (row <= rowEnd && matrix[row][col] < target) row += 1;
        if (row > rowEnd) return false;
        if (matrix[row][col] == target) return true;
        
        // down left matrix search
        if (helper(matrix, target, row, rowEnd, colStart, col-1)) return true;
        
        // top right matrix search
        return helper(matrix, target, rowStart, row-1, col+1, colEnd);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {5, 20})
    public void test_searchMatrix_Case1(int source) {
        int [][]matrix = {{1,   4,  7, 11, 15},
                          {2,   5,  8, 12, 19},
                          {3,   6,  9, 16, 22},
                          {10, 13, 14, 17, 24},
                          {18, 21, 23, 26, 30}};

        Problem240 sol = new Problem240();
        if (source == 5)
            assertTrue(sol.searchMatrix(matrix, source));
        else
            assertFalse(sol.searchMatrix(matrix, source));
    }
    
    @Test
    public void test_searchMatrix_Case2() {
        int [][]matrix = {{5}};
        Problem240 sol = new Problem240();
        assertFalse(sol.searchMatrix(matrix, -10));
    }
}