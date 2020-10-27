package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 1288> Remove Covered Intervals

Given a list of intervals, remove all intervals that are covered by another interval in the list.

Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

 

Example 1:
Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.


Example 2:
Input: intervals = [[1,4],[2,3]]
Output: 1


Example 3:
Input: intervals = [[0,10],[5,12]]
Output: 2


Example 4:
Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2


Example 5:
Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1
 

Constraints:
1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= intervals[i][0] < intervals[i][1] <= 10^5
All the intervals are unique.

======================================================
*/

public class Problem1288 {
	public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (t1, t2) -> {
            if (t1[0] < t2[0])
                return -1;
            else if (t1[0] > t2[0])
                return 1;
            else if (t1[1] > t2[1])
                return -1;
            else if (t1[1] < t2[1])
                return 1;
            return 0;
        });
        
        int []last = null;
        int cnt = 0;
        for (int []interval: intervals) {
            if (isCovered(last, interval))
               continue;
            last = interval;
            cnt += 1;
        }
        return cnt;
    }
    
    // t1 is in larger range & t2 is in smaller range
    private boolean isCovered(int []t1, int []t2) {
        if (t1 == null || t2 == null)
            return false;
        return t1[0] <= t2[0] && t1[1] >= t2[1];
    }
    
    @Test
    public void test() {
    	Problem1288 sol = new Problem1288();
    	assertEquals(2, sol.removeCoveredIntervals(new int[][] {{1,4},{3,6},{2,8}}));
    	assertEquals(1, sol.removeCoveredIntervals(new int[][] {{1,4},{2,3}}));
    	assertEquals(2, sol.removeCoveredIntervals(new int[][] {{0,10},{5,12}}));
    	assertEquals(2, sol.removeCoveredIntervals(new int[][] {{3,10},{4,10},{5,11}}));
    	assertEquals(1, sol.removeCoveredIntervals(new int[][] {{1,2},{1,4},{3,4}}));
    }
    
}


