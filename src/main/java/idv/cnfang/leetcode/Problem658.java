package idv.cnfang.leetcode;
import java.util.*;

/**
Leetcode <Problem 658> Binary Search: Find K Closest Elements

Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
*/

class Pair {
    int idx;
    int val;
    public Pair(int index, int num) {idx = index; val = Math.abs(num);}
}

class pairCompare implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) 
    { 
       int diff = p1.val - p2.val;
       if (diff != 0) return diff;
       return p1.idx-p2.idx;
    } 
}
public class Problem658 {
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> sol = new ArrayList<Integer>();
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(k, new pairCompare());
        for (int i = 0; i < arr.length; i++) pq.add(new Pair(i, arr[i]-x));
        
        for (int i = 0; i < k; i++) {
            Pair p = pq.poll();
            sol.add(arr[p.idx]);
        }
        sol.sort(null);
        return sol;
    }
        
    public static void main(String []args) {
        int []nums = {1,2,3,4,5};
        Problem658 sol = new Problem658();
        System.out.println(sol.findClosestElements(nums, 4, -1));
    }
}