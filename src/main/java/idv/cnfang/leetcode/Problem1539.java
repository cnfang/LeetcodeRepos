package idv.cnfang.leetcode;
import java.util.*;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
Leetcode <Problem 1539> Kth Missing Positive Number

Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

 

Example 1:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.


Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

================== Solution =================

*/


public class Problem1539 {
	
	public int findKthPositive(int[] arr, int k) {
        Function<Integer, Integer> getNumOfMissingElement = (index) -> arr[index] - index - 1;
        int lastIdx = arr.length - 1;
        int missingNum = getNumOfMissingElement.apply(lastIdx);
        
        // [Case 1]: NO element is missing or k is larger than number of missingElements in array
        if (k > missingNum)
            return arr[lastIdx] + (k - missingNum);
        
        
        // [Case 2]: k is smaller than missingNum, search number in arr
        // use binary search to find the index T
        // where number of missing elements (index 0 to index T) are largest but smaller than k
        int startIdx = 0, endIdx = lastIdx;
        while (endIdx >= startIdx) {
            int midIdx = (startIdx + endIdx) >> 1;
            missingNum = getNumOfMissingElement.apply(midIdx);
            if (missingNum >= k)
                endIdx = midIdx - 1;
            else
                startIdx = midIdx + 1;
        }
        // T = startIdx - 1
        if (startIdx == 0)
            return k;
        missingNum = getNumOfMissingElement.apply(startIdx - 1);
        return arr[startIdx - 1] + (k - missingNum);
    }
	
	@Test
	public void test1() {
		int []arr = {1,2,3,4};
		int k = 2;
		Problem1539 sol = new Problem1539();
		assertEquals(6, sol.findKthPositive(arr, k));
	}
	
	@Test
	public void test2() {
		int []arr = {4};
		int k = 3;
		Problem1539 sol = new Problem1539();
		assertEquals(3, sol.findKthPositive(arr, k));
	}
	
	@Test
	public void test3() {
		int []arr = {5};
		int k = 7;
		Problem1539 sol = new Problem1539();
		assertEquals(8, sol.findKthPositive(arr, k));
	}
	
	@Test
	public void test4() {
		int []arr = {2,3,4,7,11};
		int k = 5;
		Problem1539 sol = new Problem1539();
		assertEquals(9, sol.findKthPositive(arr, k));
	}
}