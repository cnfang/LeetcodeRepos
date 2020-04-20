package idv.cnfang.leetcode;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
Leetcode <Problem 678> Valid Parenthesis String

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True


Example 2:
Input: "(*)"
Output: True


Example 3:
Input: "(*))"
Output: True


Note:
The string size will be in the range [1, 100].
*/


public class Problem678 {
    
    public boolean checkValidString(String s) {
        if (s == null)
            return false;
        return backtrack(new ArrayDeque<>(), s.toCharArray(), 0);
       
    }

    private boolean backtrack(ArrayDeque<Character> queue, char[] charArray, int currIdx) {
        // base case
        if (currIdx == charArray.length)
            return queue.isEmpty()? true: false;
        
        if (charArray[currIdx] == '(') {
            queue.add(charArray[currIdx]);
            
            if (backtrack(queue, charArray, currIdx+1))
                return true;
            
            // undo current action
            queue.pollLast();
        }
        
        else if (charArray[currIdx] == ')') {
            // pop queue
            Character pre = queue.poll();
            if (pre == null)
                return false;
            if (pre != '(') {
                // undo current action
                queue.add(pre);
                return false;
            }
            
            if (backtrack(queue, charArray, currIdx+1))
                return true;
            
            // undo current action
            if (pre == null) ;
            else queue.add(pre);
        } 
        
        else {
            // case 1: if * is '' empty character, do nothing
            if (backtrack(queue, charArray, currIdx+1))
                return true;
            
            // case 2: if * is ( left parenthesis
            queue.add('(');
            if (backtrack(queue, charArray, currIdx+1))
                return true;
            
            // case 3: if * is ) right parenthesis
            queue.pollLast();
            Character pre = queue.poll();
            if (pre == null) 
                return false;
            if (pre != '(') {
                // undo current action
                queue.add(pre);
                return false;
            }
            if (backtrack(queue, charArray, currIdx+1))
                return true;
            
            // undo current action
            if (pre == null) ;
            else queue.add(pre);
        }
        return false;
    }
    
    @Test
    public void test_example1() {
        String s = "()";
        Problem678 sol = new Problem678();
        assertTrue(sol.checkValidString(s));
    }
    
    @Test
    public void test_example2() {
        String s = "(*)";
        Problem678 sol = new Problem678();
        assertTrue(sol.checkValidString(s));
    }
    
    @Test
    public void test_example3() {
        String s = "(*))"; // (())
        Problem678 sol = new Problem678();
        assertTrue(sol.checkValidString(s));
    }
    
    @Test
    public void test_example4() {
        String s = "())";
        Problem678 sol = new Problem678();
        assertFalse(sol.checkValidString(s));
    }
    
    @Test
    public void test_example5() {
        String s = "(*()";
        Problem678 sol = new Problem678();
        assertTrue(sol.checkValidString(s));
    }
}