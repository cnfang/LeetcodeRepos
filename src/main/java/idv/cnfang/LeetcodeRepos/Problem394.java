package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Stack;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 394> Queue & Stack : Decode String

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

====================== Solution ======================
use stack to store prefix
*/

public class Problem394 {
    public String decodeString(String s) {
        Stack<StringBuilder> prefix = new Stack<StringBuilder>();
        Stack<Integer> occurance = new Stack<Integer>();
        
        StringBuilder outs = new StringBuilder();
        
        char []array = s.toCharArray();
        int n = array.length;
        int i = 0;
        int j = 0;
        
        while(i < n) {
            if (Character.isDigit(array[i])) {
                j = 0;
                while(Character.isDigit(array[i])) {
                    j = j*10 + (int)array[i] - 48;
                    i++;
                }
                occurance.add(j);
            }
            else if (array[i] == '[') {
                StringBuilder newPrefix = new StringBuilder();
                prefix.add(newPrefix);
                i++;
            }
            else if (Character.isAlphabetic(array[i])) {
                if (prefix.empty()) {
                    outs.append(array[i]);
                    i++;
                }
                else {
                    StringBuilder currPrefix = prefix.pop();
                    while(Character.isAlphabetic(array[i])) {
                        currPrefix.append(array[i]);
                        i++;
                    }
                    prefix.add(currPrefix);
                }
            }
            else if (array[i] == ']') {
                StringBuilder currPrefix = prefix.pop();
                int repeatTimes = occurance.pop();
                i++;
                String suffix = String.join("", Collections.nCopies(repeatTimes, currPrefix.toString()));
                if (prefix.isEmpty())
                    outs.append(suffix);
                else {
                    StringBuilder prePrefix = prefix.pop();
                    prePrefix.append(suffix);
                    prefix.add(prePrefix);
                }
            }
        }
        
        return outs.toString();
    }
    
    @Test
    public void test_decodeString_Case1() {
        String s = "3[a]2[bc]";
        Problem394 sol = new Problem394();
        String expected = "aaabcbc";
        assertEquals(expected, sol.decodeString(s));
    }
    
    @Test
    public void test_decodeString_Case2() {
        String s = "3[a2[c]]";
        Problem394 sol = new Problem394();
        String expected = "accaccacc";
        assertEquals(expected, sol.decodeString(s));
    }
    
    @Test
    public void test_decodeString_Case3() {
        String s = "2[abc]3[cd]ef";
        Problem394 sol = new Problem394();
        String expected = "abcabccdcdcdef";
        assertEquals(expected, sol.decodeString(s));
    }
}