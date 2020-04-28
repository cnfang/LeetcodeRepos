package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 221> Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

*/

public class Problem221 {
    public int maximalSquare(char[][] matrix) {
        int len = getSquareSideLength(matrix);
        if (len == 0)
            return 0;
        return (len+1)*(len+1);
    }
    
    private int getSquareSideLength(char [][]matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        
        
        if (numRow == 1) {
            for (int i = 0; i < numCol; i++)
                if (matrix[0][i] == '1')
                    return 1;
            return 0;
        } else if (numCol == 1) {
            for (int i = 0; i < numRow; i++)
                if (matrix[i][0] == '1')
                    return 1;
            return 0;
        } else {
            int oneCnt = 0;
            char [][] out = new char[numRow-1][numCol-1];
            for (int r = 0; r < numRow-1; r++)
                for (int c = 0; c < numCol-1; c++) {
                    out[r][c] = '0';
                    if (matrix[r][c] == '1' && matrix[r][c+1] == '1' && 
                        matrix[r+1][c] == '1' && matrix[r+1][c+1] == '1') {
                        out[r][c] = '1';
                        oneCnt++;
                    }
                }
            if (oneCnt == 0)
                return 0;
            return getSquareSideLength(out)+1;
        }
    }
    
    @Test
    public void test_example1() {
        char [][]matrix = {{'1','0','1','0','0'}, 
                           {'1','0','1','1','1'},
                           {'1','1','1','1','1'},
                           {'1','0','0','1','0'}};
        int expected = 4;
        Problem221 sol = new Problem221();
        assertEquals(expected, sol.maximalSquare(matrix));
    }
    
    @Test
    public void test_example2() {
        char [][]matrix = {{'1','0','1','0','0'}, 
                           {'1','0','1','1','1'},
                           {'1','1','1','1','1'},
                           {'1','1','1','1','0'},
                           {'1','1','1','0','0'}};
        int expected = 9;
        Problem221 sol = new Problem221();
        assertEquals(expected, sol.maximalSquare(matrix));
    }
    
    @Test
    public void test_example3() {
        char [][]matrix = {{'0','0'}, 
                           {'0','0'},
                          };
        int expected = 0;
        Problem221 sol = new Problem221();
        assertEquals(expected, sol.maximalSquare(matrix));
    }
    
}