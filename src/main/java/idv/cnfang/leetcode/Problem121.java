package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 121> Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

==========================================

*/

public class Problem121 {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxP = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) min = prices[i];
            else if (prices[i] - min > maxP) maxP = prices[i] - min;
        }
        
        return maxP;
    }
    
    @Test
    public void test_maxProfit_Case1() {
        Problem121 sol = new Problem121();
        int []prices = {7,1,5,3,6,4};
        int expected = 5;
        assertEquals(expected, sol.maxProfit(prices));
    }
    
    @Test
    public void test_maxProfit_Case2() {
        Problem121 sol = new Problem121();
        int []prices = {7,6,4,3,1};
        int expected = 0;
        assertEquals(expected, sol.maxProfit(prices));
    }
    
    @Test
    public void test_maxProfit_Case3() {
        Problem121 sol = new Problem121();
        int []prices = {7,2,3,10,1};
        int expected = 8;
        assertEquals(expected, sol.maxProfit(prices));
    }
}