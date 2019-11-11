package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 207> Course Schedule

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

===============================================
When it comes to scheduling, we could use topological sort algorithm,
however topological sort exists only when there is no directed circle in the
graph. So key to solve to problem is to detect directed circle
*/

public class Problem207{
    class Digraph {
        LinkedList<Integer> []adj; 
        int numVertex;
        
        public Digraph(int v) {
            numVertex = v;
            adj = new LinkedList [v];
        }
        
        public void addEdge(int []edge) {
            int source = edge[1];
            int end = edge[0];
            
            if (adj[source] == null) adj[source] = new LinkedList<Integer>();
            adj[source].add(end);
            return;
        }
        
        public boolean hasCycle() {
            boolean []visited = new boolean[numVertex];
            boolean []recstack = new boolean[numVertex];
            boolean []circleExist = {false};
            for (int i = 0; i < numVertex; i++)
            {
              dfs(visited, recstack, circleExist, i);
              if (circleExist[0]) return true;
            }
                
            return false;
        }
        
        private void dfs(boolean []visited, boolean []recstack, boolean []circleExist, int v) { 
            if (recstack[v]) {
                circleExist[0] = true;
                return;
            }
            if (visited[v]) return;
            
            visited[v] = true;
            
            if (adj[v] == null || adj[v].size() == 0) return;
            
            recstack[v] = true;
            Iterator<Integer> head = adj[v].iterator();
            while (head.hasNext()) {
                int w = head.next();
                if (circleExist[0]) return;
                dfs(visited, recstack, circleExist, w);
            }
            recstack[v] = false;
            return;
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Digraph myDigraph = new Digraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            myDigraph.addEdge(prerequisites[i]);
        
        return !myDigraph.hasCycle();
    }
    
    
    @Test
    public void testcanFinish() {
        int [][]prerequisites = {{1, 0}};
        int numCourses = 2;
        Problem207 sol = new Problem207();
        
        assertTrue(sol.canFinish(numCourses, prerequisites));
    }
}