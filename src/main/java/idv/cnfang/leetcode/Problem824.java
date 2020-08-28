package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 824> Goat Latin

A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 


Example 1:
Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"


Example 2:
Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:
S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
*/

public class Problem824 {
    private static final Set<Character> vowel;
    private static final String MASTRING = "ma";
    private static final String ASTRING = "a";
    private static final String SPACE = " ";
    
    static {
        vowel = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
    }
    
    public String toGoatLatin(String S) {
        String []arr = S.split(" ");
        char first;
        StringBuilder repeatA = new StringBuilder(ASTRING);
        StringBuilder ans = new StringBuilder();
        
        for (int i = 0; i < arr.length; i++) {
            first = arr[i].charAt(0);
            
            if (!vowel.contains(first))
                ans.append(arr[i].substring(1)).append(first);
            else
                ans.append(arr[i]);
            
            ans.append(MASTRING).append(repeatA.toString()).append(SPACE);
            repeatA.append(ASTRING);
        }
        // delete last space at the end of ans
        ans.deleteCharAt(ans.length()-1);
        
        return ans.toString();
    }
    
    @Test
    public void test1() {
        Problem824 sol = new Problem824();
        assertEquals("Imaa peaksmaaa oatGmaaaa atinLmaaaaa", 
                     sol.toGoatLatin("I speak Goat Latin"));
    }
    
    @Test
    public void test2() {
        Problem824 sol = new Problem824();
        assertEquals("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa",
                     sol.toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}