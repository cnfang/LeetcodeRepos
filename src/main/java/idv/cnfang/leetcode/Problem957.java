package idv.cnfang.leetcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 957> Prison Cells After N Days

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 

Example 1:
Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: 
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]


Example 2:
Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]
 

Note:
cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9

======================================
check when the repeat pattern happens using hashmap

*/
 
public class Problem957 {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (N == 0)
            return cells;
        
        Map<Integer, Integer> book = new HashMap<>();
        List<int[]> mylist = new ArrayList<>();
        
        int []init = new int[8];
        init[0] = 0;
        init[7] = 0;
        for (int i = 1; i < 7; i++){
            if (cells[i-1] == cells[i+1])
                init[i] = 1;
            else
                init[i] = 0;
        }
        
        int M = 0;
        int []curr = init.clone();
        int hash = Arrays.hashCode(curr);
        while (!book.containsKey(hash) && M != (N-1) ) {
            int []clone = curr.clone();
            book.put(hash , M);
            mylist.add(clone);
            M += 1;
            for (int i = 1; i < 7; i++){
                if (clone[i-1] == clone[i+1])
                    curr[i] = 1;
                else
                    curr[i] = 0;
            }
            hash = Arrays.hashCode(curr);
        }
        if (M == (N-1))
            return curr;
        
        int r = (N - 1) % M;
        return mylist.get(r);
    }
    
    @Test
    public void example1() {
          
         int []cells = {0,1,0,1,1,0,0,1};
         int N = 7;
         int []expected = {0,0,1,1,0,0,0,0};
         Problem957 sol = new Problem957();
         assertThat(sol.prisonAfterNDays(cells, N), is(expected));
     }
     
     @Test
     public void example2() {
          
         int []cells = {1,0,0,1,0,0,1,0};
         int N = 1000000000;
         int []expected = {0,0,1,1,1,1,1,0};
         Problem957 sol = new Problem957();
         assertThat(sol.prisonAfterNDays(cells, N), is(expected));
     }
}
