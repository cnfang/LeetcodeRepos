package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 122> Best Time to Buy and Sell Stock II

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).


Example 1:
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.


Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.


Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

==========================================
The idea is that
- if the estimated profit (current price as sell value - buy value) is higher than last-estimated profit, we keep monitoring
prices
- if the estimated profit is lesser than last-estimated profit, time to wrap up one transaction, and set current price as buy value
*/

public class Problem122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        
        int accProfit = 0;
        int buy = prices[0];
        int largestProfit = 0;
        for (int price: prices) {
            if (price-buy > largestProfit) {
                /* set a new sell record, wait for next sell price */
                largestProfit = price - buy;
            } else if (price-buy < largestProfit) {
                /* wrap up one transaction, start buy mode */
                accProfit += largestProfit;
                buy = price;
                largestProfit = 0;
            }
        }
        accProfit += largestProfit;
        return accProfit;
    }
    
    @Test
    public void test_maxProfit_Case1() {
        Problem122 sol = new Problem122();
        int []prices = {7,1,5,3,6,4};
        int expected = 7;
        assertEquals(expected, sol.maxProfit(prices));
    }
    
    @Test
    public void test_maxProfit_Case2() {
        Problem122 sol = new Problem122();
        int []prices = {1,2,3,4,5};
        int expected = 4;
        assertEquals(expected, sol.maxProfit(prices));
    }
    
    @Test
    public void test_maxProfit_Case3() {
        Problem122 sol = new Problem122();
        int []prices = {7,6,4,3,1};
        int expected = 0;
        assertEquals(expected, sol.maxProfit(prices));
    }
}