package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 949> Largest Time for Given Digits

Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:
Input: [1,2,3,4]
Output: "23:41"


Example 2:
Input: [5,5,5,5]
Output: ""
 

Note:
A.length == 4
0 <= A[i] <= 9

======================================
backtracking

*/

public class Problem949 {
 
	public String largestTimeFromDigits(int[] A) {
        int []freq = new int[10];
        for (int val: A) freq[val] += 1;
        
        StringBuilder builder = new StringBuilder();
        return check(freq, builder)? builder.toString(): "";
    }
    
    private boolean check(int []freq, StringBuilder builder) {
        int len = builder.length();
        int end = 9;
        
        switch(len) {
            case 0:         // tens digit of hour
                end = 2;
                break;
            case 1:         // ones digit of hour
                end = builder.charAt(0) == '2'? 3: 9;
                break;
            case 2:         // tens digit of minute
                end = 5;
                break;
            case 3:         // ones digit of minute
                end = 9;
                break;
            case 4:         // finish recurrent checking
                builder.insert(2, ":");
                return true;
        }
        
        for (; end >= 0; end--) {
            if (freq[end] == 0)
                continue;
            freq[end] -= 1;
            builder.append(end);
            if (check(freq, builder))
                return true;
            freq[end] += 1;
            builder.deleteCharAt(len);
        }
        return false;
    }
     
     @Test
     public void test1() {
         int []A = {1,2,3,4};
         Problem949 sol = new Problem949();
         assertEquals("23:41", sol.largestTimeFromDigits(A));
     }
     
     @Test
     public void test2() {
         int []A = {5, 5, 5, 5};
         Problem949 sol = new Problem949();
         assertEquals("", sol.largestTimeFromDigits(A));
     }
}
