package idv.cnfang.leetcode;
import java.util.*;

/**

  Leetcode <Problem 336> Trie: Palindrome Pairs
  Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

  Example 1:

  Input: ["abcd","dcba","lls","s","sssll"]
  Output: [[0,1],[1,0],[3,2],[2,4]] 
  Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
  
  Example 2:

  Input: ["bat","tab","cat"]
  Output: [[0,1],[1,0]] 
  Explanation: The palindromes are ["battab","tabbat"]
  
  ====================== Solution
  https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
  The idea is to maintain a Trie of the reverse of all words.
  https://fizzbuzzed.com/top-interview-questions-5/
*/

class TrieP336 {
    private Node root;
    private static final int R = 256;
    
    class Node {
        Integer val; // index of word
        Node []next;
        Node () { next = new Node[R]; }
    }
    
    /**
     * constructor
     */
    public TrieP336() {
        root = new Node();
    }
    
    /**
     * establish trie with given keyword and val
     * @param keyword
     * @param val
     */
    public void put (String keyword, int val) {
        if (keyword == null) return;
        Node x = root;
        for (int i = 0; i < keyword.length(); i++) {
            char ch = keyword.charAt(i);
            if (x.next[ch] == null) x.next[ch] = new Node();
            x = x.next[ch];
        }
        x.val = val;
    }
    
   /**
    * retrieve all value of key that could be palindrome when paired with given keyword 
    * @param pattern
    * @return list of value of keys
    */
   public ArrayList<Integer> keysThatMatch(String keyword) {
       ArrayList<Integer> ans = new ArrayList<Integer>();
       Node x = root;
       int n = keyword.length();
       int i = 0;
       for (i = 0; i < n; i++) {
           char ch = keyword.charAt(i);
           // The matching word is shorter than or the same size as input
           if (x.val instanceof Integer && helperP336.check(keyword.substring(i, n))) ans.add(x.val);
           if (x.next[ch] == null) break;
           x = x.next[ch];
       }
       // The matching word is longer than input
       if (i == n) collect(x, new StringBuilder(), ans);
       return ans;
   }
   
   /**
    * collect all indexs of the key that are palindrome given the root node x
    * @param x, root node of trie
    * @param prefix
    * @param ans
    */
   private void collect(Node x, StringBuilder prefix, ArrayList<Integer> ans) {
       if (x == null) return;
       if (x.val instanceof Integer && helperP336.check(prefix.toString())) ans.add(x.val);
       
       for (char ch = 0; ch < R; ch++) {
           if (x.next[ch] instanceof Node) {
               prefix.append(ch);
               collect(x.next[ch], prefix, ans);
               prefix.deleteCharAt(prefix.length()-1);
           }
       }
   }
    
    
    public static void main() {
        TrieP336 rTrie = new TrieP336();
        rTrie.put("abc", 0);
        rTrie.put("bd", 1);
        rTrie.put("efk", 2);
        rTrie.put("ak", 3);
        rTrie.put("abcd", 4);
        rTrie.put("bdb", 5);
        rTrie.put("effe", 6);
        
        ArrayList<Integer> ans;
        ans = rTrie.keysThatMatch("b");
        System.out.println(ans);
        
        ans = rTrie.keysThatMatch("bd");
        System.out.println(ans);
        
        ans = rTrie.keysThatMatch("abc");
        System.out.println(ans);
    }
}

class helperP336 {
    /**
     * return a reverse string
     * @param keyword
     * @return reversed keyword
     * */
    public static String reverse(String keyword) {
        StringBuilder ans = new StringBuilder(keyword);
        ans.reverse();
        return ans.toString();
    }
    
    /**
     * check if given keyword is palindrome
     * @param keyword
     * @return true if keyword is palindrome, false if not
     */
    public static boolean check (String keyword) {
        if (keyword == null) return false;
        if (keyword.length() <= 1) return true;
        return keyword.charAt(0) == keyword.charAt(keyword.length()-1) && check(keyword.substring(1, keyword.length()-1));
    }
}

public class Problem336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieP336 rTrie = new TrieP336();
        
        // answer collection
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        
        // collection of index of palindrome, it's for empty string
        ArrayList<Integer> palindromeBag = new ArrayList<Integer>();
        
        // build a trie using reverse keyword
        for (int index = 0; index < words.length; index++) {
            rTrie.put(helperP336.reverse(words[index]), index);
            if (helperP336.check(words[index])) palindromeBag.add(index);
        }
        
        // check which pair of words are palindrome
        for (int i = 0; i < words.length; i++) {
            // case 0: edge case with illegal string
            if (words[i] == null) continue;
            
            // case 1: corner case with empty string
            else if (words[i].length() == 0) {
                for(int index: palindromeBag) {
                    if(i == index) continue;
                    ans.add(Arrays.asList(i, index));
                }
            }
            // case 2: The matching word is shorter than or the same size as input
            // case 3: The matching word is longer than input
            else {
                ArrayList<Integer> keysBag = rTrie.keysThatMatch(words[i]);
                for (int index: keysBag) {
                    if (i == index) continue;
                    ans.add(Arrays.asList(i, index));
                }
            } 
        }
        return ans;
    }
    
    public static void main(String []args) {
        String []words = {"a","b","c","ab","ac","aa"};
        Problem336 sol = new Problem336();
        List<List<Integer>> ansPair = sol.palindromePairs(words);
        System.out.println(ansPair);
        System.out.println(1>>1);
    }
}