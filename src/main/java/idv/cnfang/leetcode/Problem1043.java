package idv.cnfang.leetcode;
import java.util.*;
import java.util.stream.IntStream;
/**
Leetcode <Problem 1043> Partition Array for Maximum Sum

Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

 

Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]
 

Note:

1 <= K <= A.length <= 500
0 <= A[i] <= 10^6

*/
public class Problem1043 {
    public int maxSumAfterPartitioning(int[] A, int K) {
        if (K <= 1) return IntStream.range(0, A.length).boxed().reduce(0, (i,j)->i+j).intValue();
        
        boolean []visited = new boolean[A.length];
        int []index = IntStream.range(0, A.length).boxed().sorted((i,j)->A[i]-A[j]).mapToInt(ele->ele).toArray();
        
        for (int i = 0; i < index.length; i++) {
            
        }
        
        
    }   
    private void fillArray(int []A, boolean []visited, int index, int target, int[] K) {
        if (K[0] == 0) return;
        
        A[index] = target;
        visited[index] = true;
        
    }
    
    public static void main(String []args) {
        Problem1043 sol = new Problem1043();
        int []a = {6,2,9,10,0,-4};
        int[] sortedIndices = IntStream.range(0, a.length)
                .boxed().sorted((i, j) -> a[i] - a[j])
                .mapToInt(ele -> ele).toArray();
        
        for (int i = 0; i < a.length; i++) System.out.print(sortedIndices[i]+" ");
    }
}