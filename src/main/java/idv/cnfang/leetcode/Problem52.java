package idv.cnfang.leetcode;
import java.util.*;

/**
Leetcode <Problem 52> Recursion II : N-Queens II

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

====================== Solution ======================
apply backtracking method to rule out impossible cases
*/

public class Problem52 {
    private static char queen = 'Q';
    private static char safe = '.';
    
    public List<char [][]> totalNQueens(int n) {
        if (n <= 0) return null;
        
        // initialize map
        char [][]map = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(map[i], safe);
        
        // column case (only n possible columns)
        boolean []isColSafe = new boolean[n];
        Arrays.fill(isColSafe, true);
        
        // dale case (degree 45 with 2n-1 possible interception [1-n to n-1], j = i + interception)
        boolean []isDaleSafe = new boolean[2*n-1];
        Arrays.fill(isDaleSafe, true);
        
        // hill case (degree 135 with 2n-1 possible interception [0, 2n-1], j = -i + interception)
        boolean []isHillSafe = new boolean[2*n-1];
        Arrays.fill(isHillSafe, true);
        
        
        List<char [][]> solution = new ArrayList<char [][]>();
        backtrack(solution, map, isColSafe, isDaleSafe, isHillSafe, 0);
        return solution;
    }
    
    
    private void backtrack(List<char[][]> solution, char [][]map, boolean []isColSafe, boolean []isDaleSafe, boolean []isHillSafe, int row) {
        // base case, find the solution
        if (row == map.length) {
            int n = map.length;
            char [][]x = new char[n][n];
            for (int i = 0; i < n; i++) x[i] = map[i].clone();
            solution.add(x);
            return;
        }
        
        // keep searching for candidates
        for (int j = 0; j < map.length; j++) {
            if (is_not_under_attack(map, isColSafe, isDaleSafe, isHillSafe, row, j)) {
                place_queen(map, isColSafe, isDaleSafe, isHillSafe, row, j);
                backtrack(solution, map, isColSafe, isDaleSafe, isHillSafe, row+1);
                remove_queen(map, isColSafe, isDaleSafe, isHillSafe, row, j);
            }
        }
        return;
    }
    
    /**
     * check if current position under attack zone.
     * @param map
     * @param row
     * @param col
     * @return
     */
    private boolean is_not_under_attack(char [][]map, boolean []isColSafe, boolean []isDaleSafe, boolean []isHillSafe, int row, int col) {
        // daleOffset makes sure all possible interception >= 0
        int daleOffset = map.length - 1;
        int daleInterception = daleOffset + col - row;
        int hillInterception = col + row;
        
        return isColSafe[col] & isDaleSafe[daleInterception] & isHillSafe[hillInterception];
    }
    
    /**
     * explore this partial candidate solution, and mark the attacking zone.
     * @param map
     * @param row
     * @param col
     */
    private void place_queen(char [][]map, boolean []isColSafe, boolean []isDaleSafe, boolean []isHillSafe, int row, int col) {
        // place queen
        map[row][col] = queen;
        
        int daleOffset = map.length - 1;
        int daleInterception = daleOffset + col - row;
        int hillInterception = col + row;
        
        // mark attacking zone
        isColSafe[col] = false;
        isDaleSafe[daleInterception] = false;
        isHillSafe[hillInterception] = false;
    }
    
    /**
     * backtrack, i.e. remove the queen and remove the attacking zone.
     * @param map
     * @param row
     * @param col
     */
    private void remove_queen(char [][]map, boolean []isColSafe, boolean []isDaleSafe, boolean []isHillSafe, int row, int col) {
        // remove queen
        map[row][col] = safe;
        
        int daleOffset = map.length - 1;
        int daleInterception = daleOffset + col - row;
        int hillInterception = col + row;
        
        // unmark attacking zone
        isColSafe[col] = true;
        isDaleSafe[daleInterception] = true;
        isHillSafe[hillInterception] = true;
    }
    
    public static void main(String []args) {
        Problem52 sol = new Problem52();
        List<char [][]> map = sol.totalNQueens(4);
        
        System.out.println(map.size());
        
        for (int index = 0; index < map.size(); index++) {
            char [][]x = map.get(index);
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[0].length; j++)
                    System.out.print(x[i][j]);
                System.out.println();
            }
            System.out.println();
        }
    }
}