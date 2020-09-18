package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
Leetcode <Problem 57> Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]


Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

====================== Solution ======================
Binary Search
*/

public class Problem57 {
	
	public int[][] insert(int[][] intervals, int[] newInterval) {
        Comparator<int[]> comparator = (t1, t2) -> Integer.compare(t1[0], t2[0]);
        int insertIdx = Arrays.binarySearch(intervals, newInterval, comparator);
        insertIdx = insertIdx >= 0? insertIdx: -insertIdx-1;
        
        LinkedList<int []> ans = new LinkedList<>();
        
        // merge left
        int []last = newInterval;
        int left = insertIdx-1;
        while (left >= 0 && isOverlapped(intervals[left], last)) {
            last = mergeInterval(intervals[left], last);
            left -= 1;
        }
        for (; left >= 0; left--) {
            ans.addFirst(intervals[left]);
        }
        
        // merge right
        int right = insertIdx;
        while (right < intervals.length && isOverlapped(intervals[right], last)) {
            last = mergeInterval(intervals[right], last);
            right += 1;
        }
        ans.add(last);
        for (; right < intervals.length; right++) {
            ans.add(intervals[right]);
        }
        
        return ans.toArray(new int[ans.size()][2]);
    }
    
    
    private boolean isOverlapped(int []interval1, int []interval2) {
        if (interval2[0] < interval1[0])
            return isOverlapped(interval2, interval1);
        return interval2[0] >= interval1[0] && interval2[0] <= interval1[1];
    }
    
    
    private int[] mergeInterval(int []interval1, int []interval2) {
        if (interval2[0] < interval1[0])
            return mergeInterval(interval2, interval1);
        interval2[0] = interval1[0];
        interval2[1] = Math.max(interval1[1], interval2[1]);
        return interval2;
    }
    
    @Test
    public void test1() {
        Problem57 sol = new Problem57();
        assertArrayEquals(new int[][] {{1,5},{6,9}}, sol.insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}));
    }
    
    @Test
    public void test2() {
        Problem57 sol = new Problem57();
        assertArrayEquals(new int[][] {{1,2},{3,10},{12,16}}, sol.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}));
    }
    
}