package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 713> Subarray Product Less Than K

Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
============= Solution ==========
1. [memory limit exceed, time limit exceed]check subarray product less than k by maintaining a product array like numSubarrayProductLessThanK1, numSubarrayProductLessThanK2
2. [accepted but slow] like method 1 but turning product into log summation
3. [recommend] maintain a window where product of window less than k
*/

public class Problem713 { 
	
	// memory limit exceed
	public int numSubarrayProductLessThanK1(int[] nums, int k) {
		if (k == 0) return 0;
        ArrayList<BigInteger> productList = new ArrayList<>();
        
        int cnt = 0;
        BigInteger product = BigInteger.valueOf(1);
        BigInteger divisor = BigInteger.valueOf(k);
        productList.add(product);
        
        
        for (int num: nums) {
            product = product.multiply(BigInteger.valueOf(num));
            
            // find subarray less than k
            BigInteger minKey = product.divide(divisor); // exclusive
            int idx = Collections.binarySearch(productList, minKey);
            if (idx >= 0) {
            	while (idx+1 < productList.size() && productList.get(idx+1).equals(minKey))
            		idx += 1;
            } else {
                idx = (-idx-1)-1;
            }
            cnt += productList.size() - 1 - idx;
            
            // update product list
            productList.add(product);
        }
        return cnt;
    }
	
	// time limit exceed
	public int numSubarrayProductLessThanK2(int[] nums, int k) {
		if (k == 0) return 0;
        LinkedList<BigInteger> productList = new LinkedList<>();
        
        int cnt = 0;
        BigInteger product = BigInteger.valueOf(1);
        BigInteger divisor = BigInteger.valueOf(k);
        productList.add(product);
        
        for (int num: nums) {
            product = product.multiply(BigInteger.valueOf(num));
            
            // find subarray less than k
            BigInteger minKey = product.divide(divisor); // exclusive
            while (productList.peekFirst() != null && productList.peekFirst().compareTo(minKey) <= 0)
            	productList.pollFirst();
            cnt += productList.size();
            
            // update product list
            productList.add(product);
        }
        return cnt;
    }
	
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k == 0) return 0;
        LinkedList<Double> sumList = new LinkedList<>();
        
        int cnt = 0;
        double sum = 0;
        double logK = Math.log(k) - 1e-9;
       
        
        sumList.add(sum);
        for (int num: nums) {
            sum += Math.log(num);
            
            // find subarray less than k
            double minKey = sum - logK; // exclusive
            while (sumList.peekFirst() != null && sumList.peekFirst().compareTo(minKey) <= 0)
            	sumList.pollFirst();
            cnt += sumList.size();
            
            // update product list
            sumList.add(sum);
        }
        return cnt;
    }
    
    @Test
    public void test() {
        Problem713 sol = new Problem713();
        assertEquals(8, sol.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
        assertEquals(6, sol.numSubarrayProductLessThanK(new int[]{1,1,1}, 2));
        assertEquals(0, sol.numSubarrayProductLessThanK(new int[]{1,1,1}, 1));
        assertEquals(33, sol.numSubarrayProductLessThanK(new int[]{9,4,3,2,6,2,5,4,2,6,7}, 144));
        assertEquals(4370, sol.numSubarrayProductLessThanK(new int[] {4,32,23,1,11,21,8,3,12,21,33,5,14,11,9,14,4,27,5,7,7,14,13,9,17,10,3,4,17,31,7,19,4,7,20,5,4,3,24,30,9,11,23,16,7,21,23,22,12,20,8,20,18,11,5,8,21,5,11,31,28,21,13,21,22,3,2,1,32,8,8,27,4,26,13,14,4,15,6,10,28,15,18,20,29,1,1,12,5,18,10,9,5,13,1,2,7,3,23,26,3,4,13,9,32,32,31,26,19,8,7,29,16,21,28,14,18,5,25,3,4,33,27,3,10,10,22,13,13,32,18,11,27,12,3,33,21,3,5,23,12,23,23,20,24,31,26,7,25,1,5,28,1,1,29,7,31,20,21,32,23,24,13,21,14,30,8,5,23,26,31,31,25,33,17,30,20,20,16,16,2,8,10,5,10,16,27,8,25,5,3,22,21,30,25,20,4,10,9,4,3,20,14,13,4,3,3,1,32,29,13,25,13,28,10,26,17,29,13,28,20,22,6,6,2,30,15,2,29,21,10,28,33,22,6,25,8,18,29,17,1,25,17,27,27,28,16,18,3,4,5,28,20,32,11,23,1,33,12,7,29,32,6,33,15,33,24,13,32,15,24,20,11,27,20,7,2,21,6,11,16,27,24,22,7,4,15,16,1,25,22,16,4,9,19,16,10,14,9,30,18,17,6,8,9,23,1,9,22,20,5,1,15,30,23,3,8,10,9,22,24,9,24,9,5,20,27,16,22,7,6,11,22,27,31,16,30,8,14,12,9,2,29,33,13,18,14,30,20,19,16,33,8,31,31,30,6,15,6,2,30,15,32,17,33,14,22,2,4,23,22,21,29,8,31,26,28,26,20,12,12,18,1,20,17,8,10,10,4,5,7,28,8,31,13,11,31,17,27,27,25,10,8,30,12,17,24,19,16,31,28,15,28,17,16,7,16,1,23,7,25,11,5,19,3,1,14,10,29,19,17,29,20,20,24,5,1,29,29,14,16,2,7,29,15,14,18,14,20,4,9,30,8,28,30,18,18,15,20,6,5,2,29,3,1,29,3,6,12,16,20,27,6,5,30,14,3,9,16,15,28,2,11,22,16,12,9,15,10,22,18,13,29,31,26,31,33,21,13,20,20,12,33,15,22,7,31,24,21,21,30,7,25,31,28,25,19,8,27,27,16,31,17,12,20,30,23,15,21,1,6,10,28,10,27,31,24,9,3,23,8,30,31,2,19,8,31,3,16,30,32,1,9,9,10,30,29,12,28,20,32,22,33,2,1,18,33,32,16,9,5,21,27,29,14,6,6,21,13,23,18,15,29,21,32,29,4,9,5,33,22,7,11,17,24,10,13,4,10,1,3,11,8,12,20,18,11,8,2,25,6,25,18,1,1,21,19,11,5,2,12,7,20,20,2,31,14,26,16,15,22,6,27,6,10,20,20,31,20,13,20,12,24,15,16,23,18,3,22,22,8,20,3,5,20,16,11,11,28,2,2,32,18,28,12,32,12,27,26,30,29,4,1,22,14,7,12,23,31,11,18,7,25,12,8,24,9,14,17,25,16,4,3,29,22,17,25,17,26,22,22,19,28,2,17,24,19,18,26,9,4,25,23,15,25,23,24,29,20,18,13,22,11,9,24,12,14,6,1,22,1,21,21,8,13,15,8,29,13,2,8,17,8,27,14,6,9,29,25,31,3,10,22,30,11,19,4,12,10,6,21,27,16,22,8,32,13,6,19,30,29,26,22,2,11,8,28,17,19,10,11,19,33,23,15,12,32,31,20,5,16,11,16,4,13,24,26,18,33,14,30,33,24,19,33,19,12,27,24,29,33,13,14,10,22,25,24,32,18,5,9,6,33,27,31,26,12,16,7,30,3,12,25,11,12,28,31,5,32,13,1,5,3,26,11,25,9,28,16,3,27,13,19,18,16,12,15,18,9,4,25,6,16,14,2,10,20,22,20,20,8,7,7,5,32,30,9,27,33,22,4,13,8,6,11,18,18,27,26,27,25,15,22,16,11,13,15,25,11,29,7,3,15,26,21,27,22,29,11,16,2,25,32,24,17,11,13,15,9,33,31,14,20,22,32,4,25,13,28,33,4,9,16,20,22,6,23,7,18,30,9,12,19,32,27,33,5,22,18,9,9,6,5,5,4,4,33,16,13,5,28,24,24,6,1,14,13,12,15,10,24,22,12,10,31,33,29,24,18,18,13,25,26,32,15,3,14,14,26,19,32,15,33,33,2,6,10,24,21,21,31,31,12,22,12,6,30,32,8,16,24,26,13,13,16,5,17,26,4,4,7,27,27,5,14,24,23,32,13,3,23,10,30,23,31,30,18,25,11,24,21,7,19,13,19,4,28,22,27,2,17,12,33,32,4,25,21,23,24,7,13,21,33,7,33,31,7,20,30,13,22,19,33,16,2,20,4,31,2,31,21,12,24,23,22,2,20,19,15,2,28,4,9,30,15,13,30,4,17,24,13,32,2,12,24,24,19,11,19,6,33,2,9,26,25,16,15,24,6,15,4,28,2,3,32,18,10,2,13,24,16,13,28,28,22,33,25,21,2,5,2,21,1,8,17,2,5,18,33,29,27,20,30,27,23,25,1,8,19,33,22,30,17,24,17,7,16,16,4,25,23,4,31,12,10,30,17,17,14,4,24,10,32,13,14,15,29,17,13,7,4,21,16,23,16,2,13,12,33,23,27,33,28,15,31,25,13,12,20,14,18,18,7,31,3,31,30,33,13,6,26,13,14,33,26,4,17,13,7,15,5,18,19,24,25,15,14,33,19,26,19,16,27,20,12,6,11,6,5,17,29,17,10,25,26,10,6,8,25,5,11,11,7,32,14,2,2,32,25,12,30,20,32,18,30,19,20,30,31,11,21,13,27,22,15,9,26,4,10,4,9,2,5,5,31,14,26,32,8,30,19,16,27,6,4,12,17,18,24,13,17,16,4,5,30,11,21,21,21,4,25,26,13,2,13,2,8,32,6,12,32,9,30,15,24,11,7,26,14,10,10,22,13,28,2,21,20,19,16,1,14,16,1,26,8,3,14,4,20,1,3,21,26,25,20,1,18,17,6,16,8,17,30,24,32,9,4,24,8,23,12,28,4,32,5}, 9931));
    }
}