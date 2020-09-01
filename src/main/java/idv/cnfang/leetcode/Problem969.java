package idv.cnfang.leetcode;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 969> Pancake Sorting

Given an array of integers A, We need to sort the array performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 0 <= k < A.length.
Reverse the sub-array A[0...k].
For example, if A = [3,2,1,4] and we performed a pancake flip choosing k = 2, we reverse the sub-array [3,2,1], so A = [1,2,3,4] after the pancake flip at k = 2.

Return an array of the k-values of the pancake flips that should be performed in order to sort A. Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.

 

Example 1:
Input: A = [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k = 4): A = [1, 4, 2, 3]
After 2nd flip (k = 2): A = [4, 1, 2, 3]
After 3rd flip (k = 4): A = [3, 2, 1, 4]
After 4th flip (k = 3): A = [1, 2, 3, 4], which is sorted.
Notice that we return an array of the chosen k values of the pancake flips.


Example 2:
Input: A = [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 

Constraints:
1 <= A.length <= 100
1 <= A[i] <= A.length
All integers in A are unique (i.e. A is a permutation of the integers from 1 to A.length).

====================== Solution ======================
The idea is continuing finding the largest value within the sub-array, 
If largest value isn't at the index largest-1, but at index k
	Step 1: move the largest value of subArray to head of array (reverse array[0...k])
	Step 2: move the largest value to the right index of array (reverse array[0, largest-1])
If largest value is at the right index, do nothing
*/


public class Problem969 {
	public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }
        
        for (int largest = A.length; largest >= 1; largest--) {
            if (A[largest-1] == largest)
                continue;
            
            // move the largest value to index 0
            int k = map.get(largest);
            if (k > 0) {
            	pancakeFlip(map, A, k);
            	ans.add(k + 1);
            }
            
            // put the largest value at index A[largest-1]
            pancakeFlip(map, A, largest-1);
            ans.add(largest - 1 + 1);
        }
        return ans;
    }
    
    private void pancakeFlip(Map<Integer, Integer> map, int []A, int k) {
        // pancake reverse to index = k
        // now largest value locates at index 0
        int tmp;
        int left = 0, right = k;
        while (right > left) {
            map.put(A[left], right);
            map.put(A[right], left);
            tmp = A[right];
            A[right] = A[left];
            A[left] = tmp;
            left += 1;
            right -= 1;
        }
        return;
    }
    
    @Test
    public void test_Case1() {
        Problem969 sol = new Problem969();
        int []A = {3,2,4,1};
        List<Integer> ans = sol.pancakeSort(A);
        assertThat(A).isSorted();
        assertThat(ans).hasSizeLessThanOrEqualTo(10*A.length);
    }
    
    @Test
    public void test_Case2() {
    	Problem969 sol = new Problem969();
        int []A = {1,2,3};
        List<Integer> ans = sol.pancakeSort(A);
        assertThat(A).isSorted();
        assertThat(ans).hasSizeLessThanOrEqualTo(10*A.length);
    }
}