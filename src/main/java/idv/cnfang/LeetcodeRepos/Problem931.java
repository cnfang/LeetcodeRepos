package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 931> Minimum Falling Path Sum

Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

 

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100

======================================
apply DP to calculate the sum from bottom to top of grid

*/
//slow: 39.09% faster than java submission, 
public class Problem931 {
 
     public int minFallingPathSum(int[][] A) {
        int [][]minArray = new int [A.length][A[0].length];
        final int maxValue = 100*A.length;
        int globalMin = Integer.MAX_VALUE;
        
        for (int row = A.length-1; row >= 0; row--)
            for(int col = 0; col < A[0].length; col++) {
                if (row == A.length-1) minArray[row][col] = A[row][col];
                else {
                    int left = col-1 >= 0? minArray[row+1][col-1]: maxValue;
                    int mid = minArray[row+1][col];
                    int right = col + 1 < A[0].length ? minArray[row+1][col+1]: maxValue;
                    minArray[row][col] = A[row][col] + Math.min(left, Math.min(mid, right));
                }
            }
         
        for(int col = 0; col < A[0].length; col++)
            globalMin = minArray[0][col] < globalMin ? minArray[0][col]: globalMin;
        return globalMin;
     }
     
     @Test
     public void test_minFallingPathSum() {
          
         int [][]A = {{1,2,3},{4,5,6},{7,8,9}};
         Problem931 sol = new Problem931();
         
         int result = sol.minFallingPathSum(A);
         int expected = 12;
         assertEquals(expected, result);
     }
}


//// slow: time limit exceeded
//public class Problem931 {
//    
//    public int minFallingPathSum(int[][] A) {
//        int []min = {Integer.MAX_VALUE};
//        for (int col = 0; col < A[0].length; col++) 
//            dfs(A, min, 0, 0, col);
//        
//        return min[0];
//        
//    }
//    
//    private void dfs(int [][]A, int []min, int tmpSum, int currRow, int currCol) {
//        // base case
//        if (currRow == A.length) {
//            min[0] = tmpSum < min[0] ? tmpSum: min[0];
//            return;
//        }
//        if (currCol < 0 || currCol >= A.length) return;
//        
//        int nextSum = tmpSum + A[currRow][currCol];
//        dfs(A, min, nextSum, currRow+1, currCol-1);
//        dfs(A, min, nextSum, currRow+1, currCol);
//        dfs(A, min, nextSum, currRow+1, currCol+1);
//        return;
//    }
//    
//    public static void main(String []args) {
//         
//        int [][]A = {{1,2,3},{4,5,6},{7,8,9}};
//        Problem931 sol = new Problem931();
//        
//        int result = sol.minFallingPathSum(A);
//        int expected = 12;
//        assertEquals(expected, result);
//    }
//}
//
//
