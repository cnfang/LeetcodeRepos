package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 22> Recursion II : Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

====================== Solution ======================
apply backtracking method to rule out impossible cases

*/

public class Problem22 {
    public List<String> generateParenthesis(int n) {
        
        List<String> solution = new ArrayList<String>();
        
        backtrack(solution, new StringBuilder(), n, n);
        
        return solution;
    }
    
    private void backtrack(List<String> solution, StringBuilder prefix, int numRemainingLP, int numRemainingRP) {
       
        if (numRemainingLP > numRemainingRP) return;
        
        // base case
        if (numRemainingRP == 0 && numRemainingLP == 0) {
            solution.add(prefix.toString());
            return;
        }
        
        // check candidate '('
        if (numRemainingLP > 0) {
            place_character(prefix, '(');
            backtrack(solution, prefix, numRemainingLP-1, numRemainingRP);
            remove_character(prefix);
        }
        
        // check candidate ')'
        if (numRemainingRP > 0) {
            place_character(prefix, ')');
            backtrack(solution, prefix, numRemainingLP, numRemainingRP-1);
            remove_character(prefix);
        }
        
        
    }
    
    
    /**
     * append a character ch at the end of prefix
     * @param prefix
     * @param ch
     */
    private void place_character(StringBuilder prefix, char ch) {
        prefix.append(ch);
        return;
    }
    
    
    /**
     * remove last character of prefix
     * @param prefix
     */
    private void remove_character(StringBuilder prefix) {
        prefix.deleteCharAt(prefix.length()-1);
        return;
    }
    
    @Test
    public void test_generateParenthesis() {
        Problem22 sol = new Problem22();
        Set<String> result = new HashSet<>(sol.generateParenthesis(3));
        Set<String> expected = new HashSet<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertEquals(expected, result);
    }
    
}