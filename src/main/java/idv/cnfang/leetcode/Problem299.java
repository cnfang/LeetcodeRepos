package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 299> Bulls and Cows

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.


Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.


Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/


public class Problem299 {
	public String getHint(String secret, String guess) {
        int bullsCount = 0, cowsCount = 0;
        int []sBook = new int[10];
        int []gBook = new int[10];
        char []sarr = secret.toCharArray();
        char []garr = guess.toCharArray();
        
        for (int i = 0; i < sarr.length; i++) {
            if (sarr[i] == garr[i]) {
                bullsCount += 1;
                continue;
            }
            sBook[sarr[i] - '0'] += 1;
            gBook[garr[i] - '0'] += 1;
        }
        for (int i = 0; i < 10; i++) {
            cowsCount += Math.min(sBook[i], gBook[i]);
        }
        
        return bullsCount + "A" + cowsCount + "B";
    }
	
	@Test
	public void test() {
		Problem299 sol = new Problem299();
		assertEquals("1A3B", sol.getHint("1807", "7810"));
		assertEquals("1A1B", sol.getHint("1123", "0111"));
	}
}