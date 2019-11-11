package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 841> Queue & Stack : Keys and Rooms

There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.


Example 2:
Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.
Note:

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
The number of keys in all rooms combined is at most 3000.

==========================================
1. DFS
2. BFS
*/

public class Problem841 {
    // BFS
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        boolean []visited = new boolean[N];
        ArrayDeque<Integer> waitingQueue = new ArrayDeque<Integer>();
        // access to room 0
        int key = 0;
        waitingQueue.add(key);
        
        while (! waitingQueue.isEmpty()) {
            key = waitingQueue.pop();
            visited[key] = true;
            
            List<Integer> room = rooms.get(key);
            if (room.size() == 0) continue;
            for (int access: room) 
                if (! visited[access]) waitingQueue.add(access);
        }
        
        for (boolean b: visited) if (!b) return false;
        return true;
    }
    
    // DFS
    public boolean canVisitAllRooms_DFS(List<List<Integer>> rooms) {
        int N = rooms.size();
        boolean []visited = new boolean[N];
     
        // start with room 0
        dfs(rooms, visited, 0);
        for (boolean b: visited)
            if (!b) return false;
        
        return true;
        
    }
    
    private void dfs(List<List<Integer>> rooms, boolean []visited, int roomID) {
        if (visited[roomID]) return;
        
        visited[roomID] = true;
        for (int nextRoom: rooms.get(roomID)) {
            dfs(rooms, visited, nextRoom);
        }
        return;
    }
    
    @Test
    public void test_canVisitAllRooms_BFS() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3, 0, 1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList());
        
        Problem841 sol = new Problem841();
        assertFalse(sol.canVisitAllRooms(rooms));
    }
    
    @Test
    public void test_canVisitAllRooms_DFS() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Arrays.asList());
        
        Problem841 sol = new Problem841();
        assertTrue(sol.canVisitAllRooms_DFS(rooms));
    }
    
}
