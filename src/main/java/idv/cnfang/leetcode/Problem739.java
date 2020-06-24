package idv.cnfang.leetcode;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
/**
Leetcode <Problem 739> Queue & Stack : Daily Temperatures

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

*/

public class Problem739 {
    public int[] dailyTemperatures(int[] T) {
        if (T == null)
            return null;
        if (T.length == 0)
            return new int []{};
        
        int []waitingDays = new int [T.length];
        waitingDays[T.length-1] = 0;
        int future;
        
        
        for (int i = T.length-2; i >= 0; i--) {
            future = i + 1;
            while (future < T.length && T[future] <= T[i] && waitingDays[future] > 0)
                future += waitingDays[future];
            if (T[future] > T[i])
                waitingDays[i] = future - i;
            else
                waitingDays[i] = 0;
        }
        
        return waitingDays;
    }
    
    @Test
    public void example1() {
        int []T = {73, 74, 75, 71, 69, 72, 76, 73};
        Problem739 sol = new Problem739();
        
        int []expected = {1, 1, 4, 2, 1, 1, 0, 0};
        assertThat(sol.dailyTemperatures(T), is(expected));
    }
    
    @Test
    public void example2() {
        int []T = {69, 71, 72, 73, 73, 74, 75, 76};
        Problem739 sol = new Problem739();
        
        int []expected = {1, 1, 1, 2, 1, 1, 1, 0};
        assertThat(sol.dailyTemperatures(T), is(expected));
    }
    
    @Test
    public void example3() {
        int []T = {70, 69, 68, 67, 66, 65};
        Problem739 sol = new Problem739();
        
        int []expected = {0, 0, 0, 0, 0, 0};
        assertThat(sol.dailyTemperatures(T), is(expected));
    }
}
