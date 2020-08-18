package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 210> Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

===============================================
When it comes to scheduling, we could use topological sort algorithm,
however topological sort exists only when there is no directed circle in the
graph. So key to solve to problem is to detect directed circle.

1. check if circle exists
2. if circle exists return empty array
3. if not, run dfs and return reverse postorder 
*/

public class Problem210{
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
        
        
        private void dfs(boolean []visited, boolean []recstack, boolean []circleExist, Stack<Integer> ans, int v) { 
            if (recstack[v]) {
                circleExist[0] = true;
                return;
            }
            if (visited[v]) return;
            
            visited[v] = true;
            
            if (adj[v] == null || adj[v].size() == 0) {
                ans.add(v);
                return;
             }
            
            recstack[v] = true;
            Iterator<Integer> head = adj[v].iterator();
            while (head.hasNext()) {
                int w = head.next();
                if (circleExist[0]) return;
                dfs(visited, recstack, circleExist, ans, w);
            }
            recstack[v] = false;
            ans.add(v);
            return;
        }
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Digraph myDigraph = new Digraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            myDigraph.addEdge(prerequisites[i]);
        
        boolean []visited = new boolean[numCourses];
        boolean []recstack = new boolean[numCourses];
        boolean []circleExist = {false};
        Stack<Integer> ans = new Stack<Integer>();
        for (int i = 0; i < numCourses; i++)
        {
          myDigraph.dfs(visited, recstack, circleExist, ans, i);
          if (circleExist[0]) return new int [0];
        }
        
        int []res = new int[numCourses];
        int i = 0;
        while (!ans.empty()) res[i++] = ans.pop();
        return res;
    }
    
    @Test
    public void test1() {
        int [][]prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int numCourses = 4;
        
        Problem210 sol = new Problem210();
        
        int []result = sol.findOrder(numCourses, prerequisites);
        int []expected = {0,2,1,3};
        
        assertArrayEquals(expected, result);
        Arrays.stream(result).forEach(System.out::println);
    }
    
    @Test
    public void test2() {
        int [][]prerequisites = {{1,0}};
        int numCourses = 2;
        
        Problem210 sol = new Problem210();
        
        int []result = sol.findOrder(numCourses, prerequisites);
        int []expected = {0,1};
        
        assertArrayEquals(expected, result);
        Arrays.stream(result).forEach(System.out::println);
    }
    
}