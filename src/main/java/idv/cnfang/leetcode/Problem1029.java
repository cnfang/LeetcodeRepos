package idv.cnfang.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;

/**
Leetcode <Problem 1029> Two City Scheduling

There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 

Example 1:
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110

Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.


Note:
1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
======================= Solution ===================
1. allocate people to the city with smaller cost
2. if the number of to-be-interviewed candidate in two cities are not equal, reallocate
   the candidate with smallest cost increasing fee to another city
3. keep repeat step 2 until the number of candidates between two cities are equals. 
*/

public class Problem1029 {
    
    class MyComparator implements Comparator<int []> {
        @Override
        public int compare(int []o1, int []o2) {
            int diff1 = Math.abs(o1[0]-o1[1]);
            int diff2 = Math.abs(o2[0]-o2[1]);
            if (diff1 < diff2)
                return -1;
            else if (diff1 > diff2)
                return 1;
            return 0;
        }
    }
    
    public int twoCitySchedCost(int[][] costs) {
        Comparator<int[]> myComparator = new MyComparator();
        PriorityQueue<int []> qA = new PriorityQueue<>(myComparator);
        PriorityQueue<int []> qB = new PriorityQueue<>(myComparator);
        
        int totalCost = 0;
        for (int []item: costs) {
            if (item[0] <= item[1]) {
                qA.add(item);
                totalCost += item[0];
            } else {
                qB.add(item);
                totalCost += item[1];
            }
        }
        
        int []tmp;
        while (qA.size() > qB.size()) {
            tmp = qA.poll();
            qB.add(tmp);
            totalCost +=  tmp[1] - tmp[0];
        }
        
        while (qB.size() > qA.size()) {
            tmp = qB.poll();
            qA.add(tmp);
            totalCost += tmp[0] - tmp[1];
        }
        
        return totalCost;
    }
    
    @Test 
    public void example1() {
        int [][]costs = {{10,20},{30,200},{400,50},{30,20}};
        int expected = 110;
        Problem1029 sol = new Problem1029();
        MatcherAssert.assertThat(sol.twoCitySchedCost(costs), Matchers.is(expected));
    }
}


