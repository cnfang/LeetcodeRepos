package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 451> Sort Characters By Frequency

Example 1:
Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.




Example 2:
Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.




Example 3:
Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

public class Problem451 {
    public String frequencySort(String s) {
        HashMap<Character, StringBuilder> map = new HashMap<>();
        for (char key: s.toCharArray())
            map.compute(key, (k, v) -> v == null? new StringBuilder(String.valueOf(key)): v.append(key));
        
        List<StringBuilder> mylist = new ArrayList<>(map.values());
        // sort the stringbuilder by length of builder 
        Collections.sort(mylist, (s1, s2) -> -1*Integer.compare(s1.length(), s2.length()));
        
        StringBuilder ans = new StringBuilder();
        
        mylist.forEach(ans::append);
        return ans.toString();
    }
    
    
    @Test
    public void test1() {
        String s = "tree";
        String ans1 = "eert";
        String ans2 = "eetr";
        Problem451 sol = new Problem451();
        assertEquals(ans1, sol.frequencySort(s));
    }
    
    @Test
    public void test2() {
        String s = "cccaaa";
        String ans1 = "cccaaa";
        String ans2 = "aaaccc";
        
        Problem451 sol = new Problem451();
        assertEquals(ans2, sol.frequencySort(s));
    }
}