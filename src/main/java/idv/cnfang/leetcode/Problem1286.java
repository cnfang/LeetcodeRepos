package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 1286> Iterator for Combination

Design an Iterator class, which has:

A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.
 

Example:

CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

iterator.next(); // returns "ab"
iterator.hasNext(); // returns true
iterator.next(); // returns "ac"
iterator.hasNext(); // returns true
iterator.next(); // returns "bc"
iterator.hasNext(); // returns false
 

Constraints:
1 <= combinationLength <= characters.length <= 15
There will be at most 10^4 function calls per test.
It's guaranteed that all calls of the function next are valid.

======================================================
*/

public class Problem1286 {
    
    private int []indexHead;
    private char[] charArr;
    private int N;
    private StringBuilder builder;
    private boolean isFinal;
    

    public Problem1286(String characters, int combinationLength) {
        N = combinationLength;
        charArr = characters.toCharArray();
        indexHead = new int[N];
        
        // set up initial head value
        for (int i = 0; i < N; i++)
            indexHead[i] = i;
        isFinal = false;
        builder = new StringBuilder();
    }
    
    public String next() {
        builder.setLength(0);
        
        for (int head: indexHead)
            builder.append(charArr[head]);
        
        update(N-1, 1, charArr.length-1);
        return builder.toString();
    }
    
    public boolean hasNext() {
        return this.isFinal != true;
    }
    
    private void update(int index, int carry, int maxValue) {
        if (carry == 0)
            return;
        
        if (index == -1) {
            isFinal = true;
            return;
        }
            
        if (indexHead[index] == maxValue) {
            update(index-1, carry, maxValue-1);
            indexHead[index] = index == 0? indexHead[index]: indexHead[index-1] + 1;
        } else
            indexHead[index] += 1;
        return;
    }
    
   
    public static void main(String []args) {
        String characters = "abc";
        Problem1286 sol = new Problem1286(characters, 2);
        
        assertEquals("ab", sol.next());
        assertEquals(true, sol.hasNext());
        assertEquals("ac", sol.next());
        assertEquals(true, sol.hasNext());
        assertEquals("bc", sol.next());
        assertEquals(false, sol.hasNext());
    }
}


