package idv.cnfang.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
Leetcode <Problem 49> Hash Table: Group Anagrams

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
*/

public class Problem49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        
        HashMap<String, Integer> lookup = new HashMap<String, Integer>();
        List<List<String>> sol = new ArrayList<List<String>>();
        int index = 0;
        
        for (int i = 0; i < strs.length; i++)
        {
            char []sc = strs[i].toCharArray();
            Arrays.sort(sc);
            String tmp = String.valueOf(sc);
            if (lookup.containsKey(tmp)) {
                sol.get(lookup.get(tmp)).add(strs[i]);
            }
            else {
                lookup.put(tmp, index++);
                ArrayList<String> a = new ArrayList<String>();
                a.add(strs[i]);
                sol.add(a);
            }
        }
        return sol;
    }
    
    public static void main(String []args) {
        String []strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Problem49 sol = new Problem49();
        System.out.println(sol.groupAnagrams(strs));
    }
}