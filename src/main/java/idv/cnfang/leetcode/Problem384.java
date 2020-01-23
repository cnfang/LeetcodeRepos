package idv.cnfang.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
Leetcode <Problem 384> Shuffle an Array

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/
public class Problem384 {
    private final int []nums;
    
    public Problem384(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        if (nums == null) 
            return null;
        return Arrays.copyOf(nums, nums.length);
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) 
            return null;
        List<Integer> list = Arrays
                            .stream(nums)
                            .boxed()
                            .collect(Collectors.toList());
        Collections.shuffle(list);
        
        int[] ans = list.stream().mapToInt(i->i).toArray();
        return ans;
    }
    
  
    public static void main(String []args) {
        int []nums = {1,2,3};
        Problem384 sol = new Problem384(nums);
        
        int []rnums = sol.shuffle();
        int []onums = sol.reset();
        
        Arrays.stream(rnums).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Arrays.stream(onums).forEach(n -> System.out.print(n + " "));
    }
}
