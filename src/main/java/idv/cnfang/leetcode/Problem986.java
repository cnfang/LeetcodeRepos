package idv.cnfang.leetcode;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 986> Interval List Intersections

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)


Example 1:
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]


Note:
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
*/


public class Problem986 {
   
    
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int []> mylist = new ArrayList<>();
        
        int indA = 0, indB = 0;
        
        int []pairA, pairB;
        
        while (indA < A.length && indB < B.length) {
            pairA = A[indA];
            pairB = B[indB];
            
            if (pairA[1] < pairB[0]) {
                
                indA += 1;
            
            } else if (pairB[1] < pairA[0]) {
                
                indB += 1;
            
            } else {
                
                int []newPair = new int [2];
                
                newPair[0] = Math.max(pairA[0], pairB[0]);
                
                newPair[1] = Math.min(pairA[1], pairB[1]);
                
                mylist.add(newPair);
                
                if (newPair[1] == pairA[1])
                    indA += 1;
                else
                    indB += 1;
            }
        }
        
        int [][]ans = new int [mylist.size()][2];
        for (int i = 0; i < mylist.size(); i++)
            ans[i] = mylist.get(i);
        
        return ans;
    }
    
    
    @Test
    public void example1() {
        int [][]A = {{0,2},{5,10},{13,23},{24,25}};
        int [][]B = {{1,5},{8,12},{15,24},{25,26}};
        int [][]ans = {{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}};
        Problem986 sol = new Problem986();
        assertThat(ans, is(sol.intervalIntersection(A, B)));
    }
}