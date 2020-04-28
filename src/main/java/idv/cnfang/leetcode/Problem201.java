package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
Leetcode <Problem 201> Bitwise AND of Numbers Range

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: [5,7]
Output: 4


Example 2:
Input: [0,1]
Output: 0
======================================================

*/


public class Problem201 {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n)
            return m;
        
        char[] binM = Integer.toBinaryString(m).toCharArray();
        char[] binN = Integer.toBinaryString(n).toCharArray();
        
        // if they are different length return 0
        if (binM.length != binN.length)
            return 0;
        
        // return target string of k length, same length as binM and binN 
        // target string (k length) = binM.substring(0, q) + "0".repeat(k-q);
        // where q is the largest number s.t. binM.substring(0, q) == binN.substring(0, q)
        char []ansBin = new char [binM.length];
        Arrays.fill(ansBin, '0');
        
        for (int i = 0; i < binM.length; i++) {
            if (binM[i] == binN[i])
                ansBin[i] = binM[i];
            else
                break;
        }
        
        return Integer.valueOf(new String(ansBin), 2);
    }
    
    public int rangeBitwiseAnd_LeetcodeDiscussion(int m, int n) {
        // solution shared by @dietpepsi
        // idea is the same as mine but more efficient
        int i = 0;
        for (; m != n; ++i) {
            m >>= 1;
            n >>= 1;
        }
        return n << i;
    }
    
    @Test
    public void test_example1() {
        int m = 5, n = 7;
        int expected = 4;
        Problem201 sol = new Problem201();
        assertEquals(expected, sol.rangeBitwiseAnd(m, n));
    }
    
    @Test
    public void test_example2() {
        int m = 0, n = 1;
        int expected = 0;
        Problem201 sol = new Problem201();
        assertEquals(expected, sol.rangeBitwiseAnd(m, n));
    }
}