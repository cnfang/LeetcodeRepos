package idv.cnfang.leetcode;
/**
Leetcode <Problem 542> Queue & Stack : 01 Matrix

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

====================== Solution ======================
1. use bfs to find nearest 0 from origin
2. dynamic programming
*/

public class Problem542 {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int[][] dis = new int[matrix.length][matrix[0].length];
        int range = matrix.length * matrix[0].length;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    dis[i][j] = 0;
                } else {
                    int upCell = (i > 0) ? dis[i - 1][j] : range;
                    int leftCell = (j > 0) ? dis[i][j - 1] : range;
                    dis[i][j] = Math.min(upCell, leftCell) + 1;
                }
            }
        }
        
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    dis[i][j] = 0;
                } else {
                    int downCell = (i < matrix.length - 1) ? dis[i + 1][j] : range;
                    int rightCell = (j < matrix[0].length - 1) ? dis[i][j + 1] : range;
                    dis[i][j] = Math.min(Math.min(downCell, rightCell) + 1, dis[i][j]);
                }
            }
        }
        
        return dis;
    }
    
    public static void main(String []args) {
      int [][]matrix = {{0,0,0},
                        {0,1,0},
                        {1,1,1}};
      Problem542 sol = new Problem542();
      int [][]ans = sol.updateMatrix(matrix);
      for (int i = 0; i < ans.length; i++) {
          for (int j = 0; j < ans[0].length; j++)
              System.out.print(ans[i][j] + " ");
          System.out.println();
      }
  }
}

//public class Problem542 {
//    
//    class Vertex {
//        int row;
//        int col;
//        public Vertex(int r, int c) {
//            row = r;
//            col = c;
//        }
//    }
//    
//    public int[][] updateMatrix(int[][] matrix) {
//        int numRow = matrix.length;
//        int numCol = matrix[0].length;
//        int [][] ans = new int[numRow][numCol];
//        
//        for (int row = 0; row < numRow; row++)
//            for (int col = 0; col < numCol; col++) {
//                if (matrix[row][col] == 0) continue;
//                if (quickCheck4Neighbor(matrix, new Vertex(row, col))) {
//                    ans[row][col] = 1;
//                    continue;
//                }
//                ans[row][col] = bfs(matrix, new Vertex(row, col));
//            }
//        
//        return ans;
//    }
//    
//    /**
//     * quickly check if any of 4 neighbor has 0
//     * @param map
//     * @param origin
//     * @return true if 0 exists, false if not
//     */
//    private boolean quickCheck4Neighbor(int [][]map, Vertex origin) {
//        if (origin.row-1 >= 0 && map[origin.row-1][origin.col] == 0) 
//            return true;
//        
//        if (origin.row+1 < map.length && map[origin.row+1][origin.col] == 0) 
//            return true;
//        
//        if (origin.col-1 >= 0 && map[origin.row][origin.col-1] == 0) 
//            return true;
//        
//        if (origin.col+1 < map[0].length && map[origin.row][origin.col+1] == 0) 
//            return true;
//        
//        return false;
//    }
//    /**
//     * search nearest 0 to original, return distance between them
//     * @param map
//     * @param origin
//     * @return distance to nearest 0 from origin
//     */
//    private int bfs(int [][]map, Vertex origin) {
//        int numRow = map.length;
//        int numCol = map[0].length;
//        boolean [][] visited = new boolean[numRow][numCol];
//        
//        ArrayDeque<Vertex> waitingQueue = new ArrayDeque<Vertex>();
//        waitingQueue.add(origin);
//        
//        Vertex curr = null;
//        
//        while (! waitingQueue.isEmpty()) {
//            curr = waitingQueue.pop();
//            visited[curr.row][curr.col] = true;
//            
//            if (map[curr.row][curr.col] == 0) break;
//            
//            if (curr.row-1 >= 0 && !visited[curr.row-1][curr.col]) 
//                waitingQueue.add(new Vertex(curr.row-1, curr.col));
//            
//            if (curr.row+1 < numRow && !visited[curr.row+1][curr.col]) 
//                waitingQueue.add(new Vertex(curr.row+1, curr.col));
//            
//            if (curr.col-1 >= 0 && !visited[curr.row][curr.col-1]) 
//                waitingQueue.add(new Vertex(curr.row, curr.col-1));
//            
//            if (curr.col+1 < numCol && !visited[curr.row][curr.col+1]) 
//                waitingQueue.add(new Vertex(curr.row, curr.col+1));
//            
//        }
//        
//        // check if current vertice is 0 or 1, if it's 0, find the nearest 0, if not, all 1 apparently
//        if (map[curr.row][curr.col] == 1)
//            return Integer.MAX_VALUE;
//        
//        return Math.abs(curr.row-origin.row) + Math.abs(curr.col-origin.col);
//    }
//    
//    public static void main(String []args) {
//        int [][]matrix = {{0,0,0},
//                          {0,1,0},
//                          {1,1,1}};
//        Problem542 sol = new Problem542();
//        int [][]ans = sol.updateMatrix(matrix);
//        for (int i = 0; i < ans.length; i++) {
//            for (int j = 0; j < ans[0].length; j++)
//                System.out.print(ans[i][j] + " ");
//            System.out.println();
//        }
//    }
//    
//}