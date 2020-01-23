package idv.cnfang.leetcode;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
Leetcode <Problem 8> String to Integer (atoi)

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.


Example 1:
Input: "42"
Output: 42


Example 2:
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.

Example 3:
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.


Example 4:
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.


Example 5:
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Therefore INT_MIN (−231) is returned.
*/

public class Problem8 {
    public int myAtoi(String str) {
        String strout = str.trim();
       
        if (strout.length() == 0)
            return 0;
        
        char []chars = strout.toCharArray();
        
        // illegal cases
        if (chars[0] != '+' && chars[0] != '-' && !Character.isDigit(chars[0]))
            return 0;
        
        // remove extra zeros
        StringBuilder builder = new StringBuilder();
        int start = 0;
        if (chars[0] == '+' || chars[0] == '-') {
            builder.append(chars[0]);
            start = 1;
        }
       
        while (start < chars.length && chars[start] == '0')
            start++;
        
        for (int i = start; i < chars.length; i++) {
            if (!Character.isDigit(chars[i]))
                break;
            builder.append(chars[i]);
        }
        
        // out of Integer range 
        if (builder.length() >= 12)
          if (chars[0] == '-')
              return Integer.MIN_VALUE;
          else
              return Integer.MAX_VALUE;
        
        // convert to long
        long ans = 0;
        try {
            ans = Long.valueOf(builder.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        
        if (ans > Integer.MAX_VALUE) 
            return Integer.MAX_VALUE;
        
        if(ans < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        
        return (int) ans;
    }
    
//    public int myAtoi(String str) {
//        String strout = str.trim();
//
//        // check if prefix started with "+-0123456789"
//        Pattern pattern = Pattern.compile("[\\+\\-0123456789][0-9]*");
//        Matcher match = pattern.matcher(strout);
//        
//        if (!match.find())
//            return 0;
//        
//        int start = match.start(), end = match.end();
//       
//        if (start != 0) 
//            return 0;
//        
//        // delete extra zeros
//        String extract = "";
//        int tmp = start;
//        char []chars = strout.toCharArray();
//        char symbol = chars[0];
//        if (symbol == '+' || symbol == '-')
//            tmp += 1;
//        while (tmp < end && chars[tmp] == '0')
//            tmp++;
//        extract = strout.substring(tmp, end);
//        extract = symbol == '-'? '-'+extract: extract;
//        
//        // check if string is out of range
//        if (extract.length() >= 12)
//            if (symbol == '-')
//                return Integer.MIN_VALUE;
//            else
//                return Integer.MAX_VALUE;
//        
//        // convert to long
//        long ans = 0;
//        try {
//            ans = Long.valueOf(extract);
//        } catch (NumberFormatException e) {
//            return 0;
//        }
//       
//        // check if ans out of Integer range
//        if (ans > Integer.MAX_VALUE) 
//            return Integer.MAX_VALUE;
//        else if(ans < Integer.MIN_VALUE)
//            return Integer.MIN_VALUE;
//  
//        return (int) ans;
//    }
    
    private Problem8 sol;
    
    @BeforeEach
    public void init() {
        sol = new Problem8();
    }
    
    @Test
    public void example1() {
        String str = "42";
        int expected = 42;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example2() {
        String str = "-42";
        int expected = -42;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example3() {
        String str = "4193 with words";
        int expected = 4193;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example4() {
        String str = "words and 987";
        int expected = 0;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example5() {
        String str = "-91283472332";
        int expected = -2147483648;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example6() {
        String str = ".1";
        int expected = 0;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example7() {
        String str = "20000000000000000000";
        int expected = 2147483647;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
    
    @Test
    public void example8() {
        String str = "    0000000000012345678";
        int expected = 12345678;
        assertThat(sol.myAtoi(str), equalTo(expected));
    }
}
