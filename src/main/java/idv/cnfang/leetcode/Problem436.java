package idv.cnfang.leetcode;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
Leetcode <Problem 436> Find Right Interval

Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:

You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
 

Example 1:
Input: [ [1,2] ]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.
 

Example 2:
Input: [ [3,4], [2,3], [1,2] ]
Output: [-1, 0, 1]
Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
 

Example 3:
Input: [ [1,4], [2,3], [3,4] ]
Output: [-1, 2, -1]


Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

======================================

*/
public class Problem436 {
    
	 public int[] findRightInterval(int[][] intervals) {
	        if (intervals == null || intervals.length == 0)
	            return null;
	        
	        Map<int [], Integer> indexBook = new HashMap<>();
	        for (int i = 0; i < intervals.length; i++) {
	            indexBook.put(intervals[i], i);
	        }
	        
	        int []ans = new int[intervals.length];
	        int []key = {0, 0};
	        int ansIdx, insertIdx;
	        Comparator<int[]> comparator = (i1, i2)-> Integer.compare(i1[0], i2[0]);
	        
	        // sort the intervals using start point
	        Arrays.sort(intervals, comparator);
	        
	        // search the insertion index using the end point of interval
	        for (int i = 0; i < intervals.length; i++) {
	            ansIdx = indexBook.get(intervals[i]);
	            
	            key[0] = intervals[i][1];
	            insertIdx = Arrays.binarySearch(intervals, 
	                                            key, 
	                                            comparator);
	            insertIdx = insertIdx >= 0? insertIdx: (-insertIdx-1);
	            ans[ansIdx] = insertIdx == intervals.length ? 
	                          -1: indexBook.get(intervals[insertIdx]);
	        }
	        return ans;
	}
    
	 @Test
     public void test1() {
        Problem436 sol = new Problem436();
        int [][]intervals = {{1,2}};
        assertArrayEquals(new int[] {-1}, sol.findRightInterval(intervals));
 	 }
	 
	 @Test
     public void test2() {
        Problem436 sol = new Problem436();
        int [][]intervals = {{3,4}, {2,3}, {1,2}};
        assertArrayEquals(new int[] {-1,0,1}, sol.findRightInterval(intervals));
 	 }
	 
    @Test
    public void test3() {
        Problem436 sol = new Problem436();
        int [][]intervals = {{1,4}, {2,3}, {3,4}};
        assertArrayEquals(new int[] {-1,2,-1}, sol.findRightInterval(intervals));
    }
}