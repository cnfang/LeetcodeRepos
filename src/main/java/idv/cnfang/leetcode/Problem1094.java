package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1094> Car Pooling
You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

 

Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false


Example 2:
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Example 3:
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true


Example 4:
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true


Constraints:
trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
==========================================
check if any time point exceeding capacity
*/

public class Problem1094 {
    
	public boolean carPooling(int[][] trips, int capacity) {
        int [][]dest = trips.clone();
        
        // sort in ascending order usng starting point
        Arrays.sort(trips, (t1, t2) -> Integer.compare(t1[1], t2[1]));
        
        // sort in ascending order using destination point
        Arrays.sort(dest, (t1, t2) -> Integer.compare(t1[2], t2[2]));
        
        int picking = 0, dropping = 0;
        int []pickUp = null, dropOff = null;
        int timeCapacity = 0;
        
        while (picking < trips.length && dropping < trips.length && timeCapacity <= capacity) {
            pickUp = trips[picking];
            dropOff = dest[dropping];
            
            if (pickUp[1] < dropOff[2]) {
                timeCapacity += pickUp[0];
                picking += 1;
            } else {
                timeCapacity -= dropOff[0];
                dropping += 1;
            }    
        }
        
        for (; picking < trips.length && timeCapacity <= capacity; picking++) {
            pickUp = trips[picking];
            timeCapacity += pickUp[0];
        }
        
        return timeCapacity <= capacity;
    }
    
    @Test 
    public void test() {
        Problem1094 sol = new Problem1094();
        assertFalse(sol.carPooling(new int [][]{{2,1,5},{3,3,7}}, 4));
        assertTrue(sol.carPooling(new int [][]{{2,1,5},{3,3,7}}, 5));
        assertTrue(sol.carPooling(new int [][]{{2,1,5},{3,5,7}}, 3));
        assertTrue(sol.carPooling(new int [][]{{3,2,7},{3,7,9},{8,3,9}}, 11));
    }
}

//// share by Leetcode solution
//public boolean carPooling(int[][] trips, int capacity) {
//    int[] timestamp = new int[1001];
//    for (int[] trip : trips) {
//        timestamp[trip[1]] += trip[0];
//        timestamp[trip[2]] -= trip[0];
//    }
//    int ued_capacity = 0;
//    for (int number : timestamp) {
//        ued_capacity += number;
//        if (ued_capacity > capacity) {
//            return false;
//        }
//    }
//    return true;
//}