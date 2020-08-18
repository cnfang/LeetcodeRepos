package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 227> Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7


Example 2:
Input: " 3/2 "
Output: 1


Example 3:
Input: " 3+5 / 2 "
Output: 5


Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

public class Problem227 {
    public int calculate(String s) {
        LinkedList<Integer> operand = new LinkedList<>();
        LinkedList<Character> operator = new LinkedList<>();
        operand.add(0);
        
        for (char c: s.toCharArray()) {
            if (c == ' ')
                continue;
            else if (Character.isDigit(c)) {
                int num = operand.removeLast();
                operand.add(num*10 + c - '0');
            } else {
                operand.add(0);
                operator.add(c);
            }
        }
        
        while (operand.size() > 1) {
            int o1 = getOperand(operand, operator);
            Character c = operator.isEmpty()? null: operator.removeFirst();
            if (c == null)
                return o1;
            
            int o2 = getOperand(operand, operator);
            if (c.equals('+'))
                operand.addFirst(o1 + o2);
            else
                operand.addFirst(o1 - o2);
        }
        return operand.removeLast();
    }
    
    private int getOperand(LinkedList<Integer> operand, 
                           LinkedList<Character> operator) {
        
        Character symbol = operator.peekFirst();
        
        while (symbol !=  null && (symbol.equals('*') || symbol.equals('/'))) {
            operator.removeFirst();
            int o1 = operand.removeFirst();
            int o2 = operand.removeFirst();
            if (symbol == '*')
                operand.addFirst(o1*o2);
            else
                operand.addFirst(o1/o2);
            
            symbol = operator.peekFirst();
        }
        
        return operand.isEmpty()? 0: operand.removeFirst();
    }
    
    private static Problem227 sol = new Problem227();
    
    @Test
    public void test1() {
        String s = "3+2*2";
        assertThat(sol.calculate(s))
            .isEqualTo(7);
    }
    
    @Test
    public void test2() {
        String s = "3/2";
        assertThat(sol.calculate(s))
            .isEqualTo(1);
    }
    
    @Test
    public void test3() {
        String s = "3+5  /  2";
        assertThat(sol.calculate(s))
            .isEqualTo(5);
    }
    
    @Test
    public void test4() {
        String s = "3*9+1*2";
        assertThat(sol.calculate(s))
            .isEqualTo(29);
    }
    
    @Test
    public void test5() {
        String s = "3*9+  2 - 1";
        assertThat(sol.calculate(s))
            .isEqualTo(28);
    }
    
}