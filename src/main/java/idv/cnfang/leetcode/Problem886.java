package idv.cnfang.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
Leetcode <Problem 886> Possible Bipartition

Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:
Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]


Example 2:
Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false


Example 3:
Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false


Note:
1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].

===================== Solution ======================
1. backtracking with sorted array, past 57/66 test cases, fails on time limit exceeded
*/


public class Problem886 {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        if (dislikes.length == 0)
            return true;
        
        Arrays.sort(dislikes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])
                    return -1;
                else if (o1[0] > o2[0])
                    return 1;
                else if (o1[1] < o2[1])
                    return -1;
                else if (o1[1] > o2[1])
                    return 1;
                return 0;
            }   
        });
        int []ancestor = new int[N+1];
        Arrays.fill(ancestor, -1);
        
        // assign 1 for group 1, assign 2 for group 2, -1 for undecided
        ancestor[dislikes[0][0]] = 1;
        ancestor[dislikes[0][1]] = 2;
        
        return backtrack(dislikes, ancestor, 1);
    }
    
    private boolean backtrack(int[][] dislikes, int[] ancestor, int index) {
        if (index == dislikes.length)
            return true;
        
        int ancestor0 = ancestor[dislikes[index][0]];
        int ancestor1 = ancestor[dislikes[index][1]];
        

        if (ancestor0 == -1 && ancestor1 == -1) {
            ancestor[dislikes[index][0]] = 1;
            ancestor[dislikes[index][1]] = 2;
            if (backtrack(dislikes, ancestor, index+1))
                return true;
            ancestor[dislikes[index][0]] = 2;
            ancestor[dislikes[index][1]] = 1;
            if (backtrack(dislikes, ancestor, index+1))
                return true;
            ancestor[dislikes[index][0]] = -1;
            ancestor[dislikes[index][1]] = -1;
            return false;
            
        } else if (ancestor0 == -1) {
            ancestor[dislikes[index][0]] = 3-ancestor1;
            if (backtrack(dislikes, ancestor, index+1))
                return true;
            ancestor[dislikes[index][0]] = -1;
            return false;

        } else if (ancestor1 == -1) {
            ancestor[dislikes[index][1]] = 3-ancestor0;
            if (backtrack(dislikes, ancestor, index+1))
                return true;
            ancestor[dislikes[index][1]] = -1;
            return false;
        } else if (ancestor0 == ancestor1)
            return false;
        
        return backtrack(dislikes, ancestor, index+1);
    }
    
    
    @Disabled @Test
    public void example1() {
        int N = 4;
        int [][]dislikes = {{1,2},{1,3},{2,4}};
        Problem886 sol = new Problem886();
        assertThat(sol.possibleBipartition(N, dislikes), is(true));
    }
    
    @Disabled @Test
    public void example2() {
        int N = 3;
        int [][]dislikes = {{1,2},{1,3},{2,3}};
        Problem886 sol = new Problem886();
        assertThat(sol.possibleBipartition(N, dislikes), is(false));
    }
    
    @Disabled @Test
    public void example3() {
        int N = 10;
        int [][]dislikes = {{6,9},{1,3},{4,8},{5,6},{2,8},{4,7},{8,9},{2,5},{5,8},{1,2},{6,7},{3,10},{8,10},{1,5},{3,6},{1,10},{7,9},{4,10},{7,10},{1,4},{9,10},{4,6},{2,7},{6,8},{5,7},{3,8},{1,8},{1,7},{7,8},{2,4}};
        Problem886 sol = new Problem886();
        assertThat(sol.possibleBipartition(N, dislikes), is(false));
    }
    
    @Test
    public void example4() {
        int N = 10;
        int [][]dislikes = {{5,9},{4,7},{1,3},{4,8},{2,8},{6,9},{5,8},{3,10},{6,10},{8,10},{7,10},{7,9},{4,10},{3,5},{9,10},{3,9},{2,3},{1,9},{6,8},{2,7},{5,10},{4,6},{5,7},{3,8},{1,8},{7,8}};
        Problem886 sol = new Problem886();
        assertThat(sol.possibleBipartition(N, dislikes), is(false));
    }
}
