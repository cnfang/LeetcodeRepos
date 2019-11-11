package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 17> Recursion II : Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

====================== Solution ======================
apply backtracking method to rule out impossible cases

*/
public class Problem17 {
    public List<String> letterCombinations(String digits) {
        List<String> solution = new ArrayList<String>();
        HashMap<Character, String> book = new HashMap<Character, String>();
        bookInitialization(book);
        
        if (digits.length() > 0) backtrack(book, solution, digits, 0, new StringBuilder());
        return solution;
        
    }
    
    private void bookInitialization(HashMap<Character, String> book) {
        book.put('2', "abc");
        book.put('3', "def");
        book.put('4', "ghi");
        book.put('5', "jkl");
        book.put('6', "mno");
        book.put('7', "pqrs");
        book.put('8', "tuv");
        book.put('9', "wxyz");
        return;
    }
    private void backtrack(HashMap<Character, String> book, List<String> solution, String digits, int startIndex, StringBuilder prefix) {
        
        // base case: stop searching
        if (startIndex == digits.length()) {
            solution.add(prefix.toString());
            return;
        }
        
        char []letterCollection = book.get(digits.charAt(startIndex)).toCharArray();
        for (char letter: letterCollection) {
            // place letter
            prefix.append(letter);
            
            // backtrack: search next level 
            backtrack(book, solution, digits, startIndex+1, prefix);
            
            // remove letter
            prefix.deleteCharAt(prefix.length()-1);
        }
        
    }
    
    @Test
    public void testletterCombinations() {
        String digits = "23";
        Problem17 sol = new Problem17();
        List<String> result = sol.letterCombinations(digits);
        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        assertEquals(expected, result);
    }
}