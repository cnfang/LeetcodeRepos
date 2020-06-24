package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 174> Dungeon Game

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.


Note:
The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

========= Solution ==========
1. DP (pass 41/45 testcases), dp from left-top to right-down is wrong
2. DP reversing the order
*/

public class Problem174 {
    
    class Node {
        int minV;
        int path;
        public Node(int path, int minV) {this.minV = minV; this.path = path;}
    }
    
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 1;
        
        int numRow = dungeon.length, numCol = dungeon[0].length;
        Node [][]matrix = new Node[numRow][numCol];
        
        for (int r = 0; r < numRow; r++)
            for (int c = 0; c < numCol; c++)
                matrix[r][c] = generateNode(dungeon, matrix, r, c);
        
        System.out.println("path = " + matrix[numRow-1][numCol-1].path + ", min = "+ matrix[numRow-1][numCol-1].minV);
        return matrix[numRow-1][numCol-1].minV + 1;
    }
    
    private Node generateNode(int [][]dungeon, Node [][]matrix, int r, int c) {
        if (r == 0 && c == 0)
            return new Node(dungeon[r][c], dungeon[r][c] >= 0? 0: -dungeon[r][c]);
        else if (r == 0) {
            int pathSum = matrix[r][c-1].path + dungeon[r][c];
            int minV = matrix[r][c-1].minV;
            if (pathSum < 0 && Math.abs(pathSum) > minV)
                minV = Math.abs(pathSum);
            return new Node(pathSum, minV);
        }
        else if (c == 0) {
            int pathSum = matrix[r-1][c].path + dungeon[r][c];
            int minV = matrix[r-1][c].minV;
            if (pathSum < 0 && Math.abs(pathSum) > minV)
                minV = Math.abs(pathSum);
            return new Node(pathSum, minV);
        }
        
        int pathSumU = matrix[r-1][c].path + dungeon[r][c];
        int minVU = matrix[r-1][c].minV;
        if (pathSumU < 0 && Math.abs(pathSumU) > minVU)
            minVU = Math.abs(pathSumU);
        
        int pathSum = pathSumU;
        int minV = minVU;
        
        int pathSumL = matrix[r][c-1].path + dungeon[r][c];
        int minVL = matrix[r][c-1].minV;
        if (pathSumL < 0 && Math.abs(pathSumL) > minVL)
            minVL = Math.abs(pathSumL);
        
        if (minVL < minV) {
            minV = minVL;
            pathSum = pathSumL;
        } else if (minVL == minV) {
            pathSum = Math.max(pathSum, pathSumL);
        }
        
        return new Node(pathSum, minV);
    }
   
    @Test
    public void example1() {
        Problem174 sol = new Problem174();
        int [][]dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        int expected = 7;
        assertEquals(expected, sol.calculateMinimumHP(dungeon));
    }
    
    @Test
    public void example2() {
        Problem174 sol = new Problem174();
        int [][]dungeon = {{1,-3,3},{0,-2,0},{-3,-3,-3}};
        int expected = 3;
        assertEquals(expected, sol.calculateMinimumHP(dungeon));
    }
    
}