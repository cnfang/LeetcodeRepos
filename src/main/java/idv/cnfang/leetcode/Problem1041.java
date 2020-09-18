package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1141> Robot Bounded In Circle

On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:
Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.


Example 2:
Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.


Example 3:
Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 

Note:
1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/

public class Problem1041 {
	// counterclockwise
    private static int [][]direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int TOTALDIR = 4;
    
    public boolean isRobotBounded(String instructions) {
        int currentDir = 0;
        int []position = {0, 0};
        for (char ch: instructions.toCharArray()) {
            switch(ch) {
                case 'G':
                    position[0] += direction[currentDir][0];
                    position[1] += direction[currentDir][1];
                    break;
                case 'L':
                    currentDir += LEFT;
                    currentDir = Math.floorMod(currentDir, TOTALDIR);
                    break;
                case 'R':
                    currentDir += RIGHT;
                    currentDir = Math.floorMod(currentDir, TOTALDIR);
                    break;
                default:
                    break;
            }
        }
        
        return (position[0] == 0 && position[1] == 0) || currentDir > 0;
    }
    
    
    @Test
    public void test() {
    	Problem1041 sol = new Problem1041();
    	assertTrue(sol.isRobotBounded("GGLLGG"));
    	assertFalse(sol.isRobotBounded("GG"));
    	assertTrue(sol.isRobotBounded("GL"));
    }
}