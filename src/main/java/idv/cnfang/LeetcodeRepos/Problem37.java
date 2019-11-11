package idv.cnfang.leetcode;

import java.util.*;

/**
Leetcode <Problem 37> Recursion II : Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

====================== Solution ======================
apply backtracking method to rule out impossible cases

*/

public class Problem37 {
    private static char emptyChar = '.';

    class Cell {
      public final int row;
      public final int col;
      public Cell(int r, int c) {
          row = r;
          col = c;
      }
    }
    
    public void solveSudoku(char[][] board) {
        
        List<Cell> emptyCell = new LinkedList<Cell>();
        
        // put the empty cells on linkedlist that waited to be solved
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == emptyChar) 
                    emptyCell.add(new Cell(i, j));
      
        Cell currCell = emptyCell.remove(0);
        backtrack(board, emptyCell, currCell);
        return;
    }

    /**
    * solve the Sudoku task given all the empty cells unsolved and current cell to be tried.
    * @param board
    * @param emptyCell
    * @param currCell
    * @return
    */
    private boolean backtrack(char [][]board, List<Cell> emptyCell, Cell currCell) {
        // base case: found the solution
        if (currCell == null)
            return true;
  
        String candidate = validCandidate(board, currCell);
  
        for (int index = 0; index < candidate.length(); index++) {
            char ch = candidate.charAt(index);
            
            Cell nextCell = place_number(board, emptyCell, currCell, ch);
            
            if (backtrack(board, emptyCell, nextCell))
                return true;
            
            remove_number(board, emptyCell, currCell, nextCell);
        }
  
        return false;
    }

    /**
    * return possible candidate given position (row, col)
    * @param board
    * @param currCell
    * @return 
    */
    private String validCandidate(char [][]board, Cell currCell) {
  
        StringBuilder candidate = new StringBuilder();
        boolean []show = new boolean[9];
        
        int rowQuotient = currCell.row/3;
        int colQuotient = currCell.col/3;
        
        // check row, column, subBlock is safe or not
        for (int i = 0; i < 9; i++) {
            if (board[currCell.row][i] != emptyChar) show[board[currCell.row][i]-49] = true;
            if (board[i][currCell.col] != emptyChar) show[board[i][currCell.col]-49] = true;
            if (board[i/3 + 3*rowQuotient][i%3 + 3*colQuotient] != emptyChar) show[board[i/3 + 3*rowQuotient][i%3 + 3*colQuotient]-49] = true;
        }
  
        for (int i = 0; i < 9; i++)
            if (!show[i]) candidate.append((char)(i+49));
  
        return candidate.toString();
    }

    /**
    * place the character ch at position currCell, and return next position to be solved
    * @param board
    * @param emptyCell
    * @param currCell
    * @param ch
    * @return
    */
    private Cell place_number(char [][]board, List<Cell> emptyCell, Cell currCell, char ch) {
        board[currCell.row][currCell.col] = ch;
        if (emptyCell.isEmpty()) return null;
        return emptyCell.remove(0);
    }

    /**
    * undo the action of putting character on position currCell, and put the to-be-solved cell back on list
    * @param board
    * @param emptyCell
    * @param currCell
    * @param nextCell
    */
    private void remove_number(char [][]board, List<Cell> emptyCell, Cell currCell, Cell nextCell) {
        board[currCell.row][currCell.col] = emptyChar;
        emptyCell.add(0, nextCell);
        return;
    }
    
    /**
     * print the board
     * @param board
     */
    public static void printSol(char [][]board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j]);
            System.out.println();
         }
        return;
    }
    
    public static void main(String []args) {
        Problem37 sol = new Problem37();
        String [][]board = {{"5","3",".",".","7",".",".",".","."},
                             {"6",".",".","1","9","5",".",".","."},
                             {".","9","8",".",".",".",".","6","."},
                             {"8",".",".",".","6",".",".",".","3"},
                             {"4",".",".","8",".","3",".",".","1"},
                             {"7",".",".",".","2",".",".",".","6"},
                             {".","6",".",".",".",".","2","8","."},
                             {".",".",".","4","1","9",".",".","5"},
                             {".",".",".",".","8",".",".","7","9"}};
        char [][] board1 = new char[9][9];
        char [][] board2 = new char [9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                board1[i][j] = board[i][j].charAt(0);
                board2[i][j] = board[i][j].charAt(0);
            }
        sol.solveSudoku(board1);
        Problem37.printSol(board1);
        
        System.out.println("Other Approach");
        Problem36_OtherSolution sol2 = new Problem36_OtherSolution();
        sol2.solveSudoku(board2);
        Problem36_OtherSolution.printSol(board2);
    }
}

class Problem36_OtherSolution {
    private static char emptyChar = '.';

    class Cell {
      public final int row;
      public final int col;
      public Cell(int r, int c) {
          row = r;
          col = c;
      }
    }
    
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
        return;
    }

    /**
    * solve the Sudoku task given all the empty cells unsolved and current cell to be tried.
    * @param board
    * @param row
    * @param col
    * @return
    */
    private boolean backtrack(char [][]board, int row, int col) {
        // base case: found the solution
        if (row == 9)
            return true;
        if (col == 9) {
            row += 1;
            col = 0;
            return backtrack(board, row, col);
        }
        
        if (board[row][col] != emptyChar) 
            return backtrack(board, row, col+1);
  
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValidCandidate(board, row,  col, ch)) continue;
            
            place_number(board, row, col, ch);
            
            if (backtrack(board, row, col+1)) return true;
            
            remove_number(board, row, col);
        }
  
        return false;
    }

    /**
    * check if character ch fits in current position
    * @param board
    * @param row
    * @param col
    * @param ch
    * @return 
    */
    private boolean isValidCandidate(char [][]board, int row, int col, char ch) {
        
        int rowQuotient = row/3;
        int colQuotient = col/3;
        
        // check row, column, subBlock is safe or not
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) return false;
            if (board[i][col] == ch) return false;
            if (board[i/3 + 3*rowQuotient][i%3 + 3*colQuotient] == ch) return false;
        }
  
        return true;
    }

    /**
    * place the character ch at position (row, col)
    * @param board
    * @param row
    * @param col
    * @param ch
    * @return
    */
    private void place_number(char [][]board, int row, int col, char ch) {
        board[row][col] = ch;
        return;
    }

    /**
    * undo the action of putting character on position (row, col)
    * @param board
    * @param row
    * @param col
    */
    private void remove_number(char [][]board, int row, int col) {
        board[row][col] = emptyChar;
        return;
    }
    
    /**
     * print the board
     * @param board
     */
    public static void printSol(char [][]board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j]);
            System.out.println();
         }
        return;
    }
    
    public static void main(String []args) {
        Problem37 sol = new Problem37();
        String [][]board0 = {{"5","3",".",".","7",".",".",".","."},
                             {"6",".",".","1","9","5",".",".","."},
                             {".","9","8",".",".",".",".","6","."},
                             {"8",".",".",".","6",".",".",".","3"},
                             {"4",".",".","8",".","3",".",".","1"},
                             {"7",".",".",".","2",".",".",".","6"},
                             {".","6",".",".",".",".","2","8","."},
                             {".",".",".","4","1","9",".",".","5"},
                             {".",".",".",".","8",".",".","7","9"}};
        char [][]board = new char[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = board0[i][j].charAt(0);
        sol.solveSudoku(board);
        printSol(board);
    }
}