package idv.cnfang.leetcode;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 967> Numbers With Same Consecutive Differences

Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.

 

Example 1:
Input: N = 3, K = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.


Example 2:
Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

Note:

1 <= N <= 9
0 <= K <= 9

====================== Solution ======================
generate map for DFS finding solution set

*/


public class Problem967 {
    private static final char SHIFT = '0';
    private static final int LAST = 10;
    
    public int[] numsSameConsecDiff(int N, int K) {
        List<Character> []map = buildLookUpMap(K);
        List<Integer> ans = new ArrayList<>();
        if (N == 1) ans.add(0);
        dfs(ans, map, N, new StringBuilder());
        return ans.stream().mapToInt(i->i).toArray();
    }
    
    private List<Character>[] buildLookUpMap(int K) {
        List<Character>[] map = new List[LAST+1];
        for (int i = 0; i < LAST; i++) {
            map[i] = new ArrayList<>();
            if (K > 0) {
                if (i + K < 10)
                    map[i].add((char)(i+K+SHIFT));
                if (i - K >= 0)
                    map[i].add((char)(i-K+SHIFT));
            } else
                map[i].add((char)(i+SHIFT));
        }
        map[LAST] = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
        return map;
    }
     
    private void dfs(List<Integer> ans, List<Character> []map, 
                     int N, StringBuilder prefix) {
        if (prefix.length() == N) {
            ans.add(Integer.valueOf(prefix.toString()));
            return;
        }
        
        int key = prefix.length() == 0? LAST: (int)(prefix.charAt(prefix.length()-1)-SHIFT);
        for (Character next: map[key]) {
            prefix.append(next);
            dfs(ans, map, N, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
        return;
    }
    
    
    @Test
    public void test_Case1() {
        Problem967 sol = new Problem967();
        assertThat(sol.numsSameConsecDiff(3, 7))
            .containsExactlyInAnyOrder(181,292,707,818,929);
    }
    
    @Test
    public void test_Case2() {
        Problem967 sol = new Problem967();
        assertThat(sol.numsSameConsecDiff(2, 1))
            .containsExactlyInAnyOrder(10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98);
    }
}