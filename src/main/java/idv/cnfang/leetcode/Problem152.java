package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeSet;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 152> Maximum Product Subarray

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

==========================================
DP, create two arrays of the same size of input array. maxDP[i](/minDP[i]) stores the max(/min) Product of the subarray beginning with nums[i].
Step 1: calculate maxDP[i] and minDP[i] from (nums.length-1) to (0)
Step 2: maxDP[i] = max(nums[i], maxDP[i+1]*nums[i], minP[i+1]*nums[i]), minDP[i] = min(nums[i], maxDP[i+1]*nums[i], minDP[i+1]*nums[i])
Step 3: loop through maxDP array and return the maximum among it

Note that: if you'd like to know which subarray has max product, keep maxDP, minDP arrays (maxProduct_SpaceLuxary function)
otherwise no need to generate maxDP and minDP arrays. just maintain the max/min so far (maxProduct function)
*/

public class Problem152 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        
        // initialization
        int maxDP = nums[n-1];
        int minDP = nums[n-1];
        int maxP = maxDP;
        
        
        int currMaxDP = maxDP;
        int currMinDP = minDP;
        
        // loop through nums array from n-2 to 0 to find the maximum subarray product
        for (int i = n-2; i >= 0; i--) {
            
            currMaxDP = Math.max(nums[i], Math.max(maxDP*nums[i], minDP*nums[i]));
            currMinDP = Math.min(nums[i], Math.min(maxDP*nums[i], minDP*nums[i]));
            
            maxDP = currMaxDP;
            minDP = currMinDP;
            if (maxDP > maxP) maxP = maxDP;
        }
        
        return maxP;
    }
    
    // Runtime 2ms 50.96% faster than Java submission, memory 37.1MB less than 100% java submission
    public int maxProduct_SpaceLuxary(int[] nums) {
        int n = nums.length;
        int []maxDP = new int [n];
        int []minDP = new int [n];
        int maxP;
        
        // initialization
        maxDP[n-1] = nums[n-1];
        minDP[n-1] = nums[n-1];
        maxP = maxDP[n-1];
       
       
        // loop through nums array from n-2 to 0 to find the maximum subarray product
        for (int i = n-2; i >= 0; i--) {
            int maxProduct = nums[i]*maxDP[i+1];
            int minProduct = nums[i]*minDP[i+1];
            maxDP[i] = Math.max(nums[i], Math.max(maxProduct, minProduct));
            minDP[i] = Math.min(nums[i], Math.min(maxProduct, minProduct));
            
            if (maxDP[i] > maxP) maxP = maxDP[i];
        }
        
        return maxP;
    }
    
    // Runtime 3ms 25.46% faster than Java submission, memory 39.3MB less than 88.23% java submission
    public int maxProduct_TreeSet(int[] nums) {
    	TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        int sum = 1, tmp = 0;
        int maxProd = Integer.MIN_VALUE, maxProdSub = Integer.MIN_VALUE;
        
        for (int num: nums) {
            sum *= num;
            if (sum == 0) {
                maxProd = Math.max(0, Math.max(maxProd, maxProdSub));
                maxProdSub = Integer.MIN_VALUE;
                set.clear();
                set.add(1);
                sum = 1;
                continue;
            } else if (sum > 0) {
                tmp = sum;
            } else if (sum < 0) {
                Integer key = set.floor(-1);
                int maxAcc = key == null? set.last(): key;
                tmp = sum / maxAcc;
            }
            maxProdSub = Math.max(tmp, maxProdSub);
            set.add(sum);
        }
        return Math.max(maxProdSub, maxProd);
    }
    
    @Test
    public void test_maxProduct_Case1() {
        Problem152 sol = new Problem152();
        int []nums = {2,3,-2,4};
        int expected = 6;
        assertEquals(expected, sol.maxProduct(nums));
    }
    
    @Test
    public void test_maxProduct_Case2() {
        Problem152 sol = new Problem152();
        int []nums = {-2,0,-1};
        int expected = 0;
        assertEquals(expected, sol.maxProduct(nums));
    }
    
    @Test
    public void test_maxProduct_Case3() {
        Problem152 sol = new Problem152();
        int []nums = {-4,-3,-2};
        int expected = 12;
        assertEquals(expected, sol.maxProduct(nums));
    }
}