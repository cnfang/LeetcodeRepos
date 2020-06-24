package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1035> Uncrossed Lines

We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

 

Example 1:
Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.


Example 2:
Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3


Example 3:
Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2
 

Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000

====================== Solution =================
Solution 1: backtracking (try all combination) fails, time limit exceeded (as below)
Solution 2: problem same as longest subsequence problem. DP is used, please refer to https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/

*/

public class Problem1035 {
    
    public int maxUncrossedLines(int[] A, int[] B) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> mapIndex = new HashMap<>();
        
        for (int idx = 0; idx < B.length; idx++) {
            if (!map.containsKey(B[idx])) {
                map.put(B[idx], new ArrayList<Integer>());
                mapIndex.put(B[idx], 0);
            }
            map.get(B[idx]).add(idx);
        }
        
        int []maxLines = {0};
        backtrack(map, mapIndex, A, maxLines, 0, -1, 0);
        
        return maxLines[0];
    }
    
    private void backtrack(HashMap<Integer, List<Integer>> map, 
                           HashMap<Integer, Integer> mapIndex, 
                           int []A, 
                           int []maxLines, 
                           int index, 
                           int currMaxIndexB, 
                           int accLines) 
    {
        if (index == A.length) {
            maxLines[0] = accLines > maxLines[0] ? accLines: maxLines[0];
            return;
        }
        
        if (!map.containsKey(A[index])) {
            backtrack(map, mapIndex, A, maxLines, index+1, currMaxIndexB, accLines);
            return;
        }
        
        // not taking current lines
        backtrack(map, mapIndex, A, maxLines, index+1, currMaxIndexB, accLines);
        
        // take current lines
        int tmpIndex = mapIndex.get(A[index]);
        int originIndex = mapIndex.get(A[index]);
        
        while (tmpIndex < map.get(A[index]).size()) {
            if (map.get(A[index]).get(tmpIndex) > currMaxIndexB)
                break;
            tmpIndex += 1;
        }
        
        if (tmpIndex < map.get(A[index]).size()) {
            mapIndex.put(A[index], tmpIndex);
            backtrack(map, mapIndex, A, maxLines, index+1, map.get(A[index]).get(tmpIndex), accLines+1);
            mapIndex.put(A[index], originIndex);
        }
        
        return;
    }
    
    
    @Test
    public void test1() {
        Problem1035 sol = new Problem1035();
        int []A = {1, 4, 2};
        int []B = {1, 2, 4};
        int expected = 2;
        assertEquals(expected, sol.maxUncrossedLines(A, B));
    }
    
    @Test
    public void test2() {
        Problem1035 sol = new Problem1035();
        int []A = {2,5,1,2,5};
        int []B = {10,5,2,1,5,2};
        int expected = 3;
        assertEquals(expected, sol.maxUncrossedLines(A, B));
    }
    
    @Test
    public void test3() {
        Problem1035 sol = new Problem1035();
        int []A = {1,3,7,1,7,5};
        int []B = {1,9,2,5,1};
        int expected = 2;
        assertEquals(expected, sol.maxUncrossedLines(A, B));
    }
    
    /**
     * time limit exceeded
     */
    @Test
    public void test4() {
        Problem1035 sol = new Problem1035();
        int []A = {19,5,19,19,2,9,5,19,20,17,3,1,7,10,19,16,8,3,13,13,16,3,16,7,14,11,18,5,8,12,8,15,18,10,8,8,12,8,9,17,17,14,14,1,8,19,8,1,5,4};
        int []B = {18,20,18,18,4,7,17,17,1,18,6,4,11,14,19,15,12,20,3,5,12,2,13,14,9,16,6,4,16,8,10,19,15,18,12,11,9,14,7,9,14,15,6,18,12,8,20,11,2,17};
        int expected = 16;
        assertEquals(expected, sol.maxUncrossedLines(A, B));
    }
    
}


