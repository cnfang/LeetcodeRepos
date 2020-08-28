package idv.cnfang.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
Leetcode <Problem 983> Minimum Cost For Tickets

In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

 

Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.


Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.
 

Note:
1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
==================================
1. [slow 19.65% runtime] backtracking all possible day-pass solution 
2. DP costLookup[i] = min(costLookip[i-1]+costs[0], costLookip[i-7]+costs[1], costLookip[i-30]+costs[2])
*/

public class Problem983 {
    
    public int mincostTickets(int[] days, int[] costs) {
        int []dayPass = {1, 7, 30};
        int []dayCost = costs;
        
        // index i indicates the minimum cost from [0, i) (exclude index i)
        int []costLookup = new int[366];
        Arrays.fill(costLookup, Integer.MAX_VALUE);
        
        backtrack(days, dayCost, dayPass, costLookup, 0, 0);
        return costLookup[days.length];
    }
    
    private void backtrack(int []days, int []dayCost, int []dayPass,
                           int []costLookup, int pathSum, int currIdx) {
        
        if (pathSum >= costLookup[currIdx])
            return;
        
        costLookup[currIdx] = pathSum;
        
        if (currIdx == days.length)
            return;
        
        // search all day-pass solutions
        for (int i = 0; i < dayCost.length; i++) {
            int idx = getMaxIdxDayPassAllows(days, currIdx, dayPass[i]);
            backtrack(days, dayCost, dayPass, costLookup, pathSum + dayCost[i], idx+1);
        }
        return;
    }
    
    /**
     * return the index of array where dayPass allows to travel from current date
     * @param days
     * @param currIdx
     * @param dayPass
     * @return
     */
    private int getMaxIdxDayPassAllows(int []days, int currIdx, int dayPass) {
        if (dayPass == 1)
            return currIdx;
        int idx = Arrays.binarySearch(days, currIdx, days.length, days[currIdx]+dayPass-1);
        idx = idx >= 0? idx: (-1*idx-1)-1;
        return idx;
    }
    
    @Test
    public void test2() {
        Problem983 sol = new Problem983();
        int []days = {1,2,3,4,5,6,7,8,9,10,30,31};
        int []costs = {2,7,15};
        assertEquals(17, sol.mincostTickets(days, costs));
    }
    
    @Test
    public void test3() {
        Problem983 sol = new Problem983();
        int []days = {1,4,6,9,10,11,12,13,14,15,16,17,18,20,21,22,23,27,28};
        int []costs = {3,13,45};
        assertEquals(44, sol.mincostTickets(days, costs));
    }
    
    @Test
    public void test4() {
        Problem983 sol = new Problem983();
        int []days = {1,2,3,4,7,8,9,10,11,13,14,15,17,18,19,20,21,23,25,26,27,28,30,31,32,33,36,37,38,39,42,45,47,49,51,52,53,54,57,58,60,61,62,63,67,68,69,71,73,75,77,78,80,81,85,87,89,90,91,92,93,97,98,99,103,104,105,108,109,111,112,114,115,116,118,119,121,124,125,128,129,130,132,133,134,136,137,138,139,140,142,143,144,145,146,150,151,153,155,156,157,159,160,161,162,164,166,167,169,170,171,173,177,179,180,181,182,185,186,187,191,192,193,194,196,198,200,201,202,203,205,209,211,215,216,218,220,221,222,223,224,225,227,228,229,230,233,235,236,238,239,241,242,243,244,245,247,248,251,253,254,255,256,257,259,264,265,266,267,268,270,271,272,273,276,277,278,279,280,281,282,286,287,288,289,290,293,294,295,296,297,298,299,300,301,304,305,306,307,309,310,311,313,315,316,317,318,319,320,321,322,325,326,328,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,348,349,350,351,352,354,355,357,358,359,361,363,364};
        int []costs = {40,210,865};
        assertEquals(8910, sol.mincostTickets(days, costs));
    }
}
