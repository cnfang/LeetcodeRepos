package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 228> Summary Ranges

You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b
 

Example 1:
Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"


Example 2:
Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"


Example 3:
Input: nums = []
Output: []


Example 4:
Input: nums = [-1]
Output: ["-1"]


Example 5:
Input: nums = [0]
Output: ["0"]
 

Constraints:
0 <= nums.length <= 20
-231 <= nums[i] <= 231 - 1
All the values of nums are unique.
*/

public class Problem228 {
	public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0)
            return Arrays.asList();
        
        List<String> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        
        int start = nums[0], end = start;
        for (int i = 1; i < nums.length; i++) {
            if (end+1 == nums[i])
                end = nums[i];
            else {
                ans.add(getRange(builder, start, end));
                builder.setLength(0);
                start = nums[i];
                end = start;
            }
        }
        
        ans.add(getRange(builder, start, end));
        return ans;
    }
    
    private String getRange(StringBuilder builder, int start, int end) {
        builder.append(start);
        if (end > start) 
            builder.append("->")
                   .append(end); 
        return builder.toString();
    }
    
    @Test
    public void test() {
    	Problem228 sol = new Problem228();
    	assertEquals(Arrays.asList("0->2","4->5","7"), sol.summaryRanges(new int[] {0,1,2,4,5,7}));
    	assertEquals(Arrays.asList("0","2->4","6","8->9"), sol.summaryRanges(new int[] {0,2,3,4,6,8,9}));
    	assertEquals(Arrays.asList(), sol.summaryRanges(new int[] {}));
    	assertEquals(Arrays.asList("-1"), sol.summaryRanges(new int[] {-1}));
    }
}