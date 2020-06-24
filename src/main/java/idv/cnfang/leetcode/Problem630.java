package idv.cnfang.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
Leetcode <Problem 630> Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5

================ Solution ===========
Using DFS to check if there is a directed cycle existing in graph or not
*/

public class Problem630 {
    static class Graph {
        final int V;
        final Set<Integer>[]adj;
        
        Graph(int numVertex) {
            this.V = numVertex;
            adj = new HashSet[this.V];
            for (int i = 0; i < this.V; i++)
                adj[i] = new HashSet<>();
        }
        
        void addEdge(int []edge) {
            adj[edge[0]].add(edge[1]);
        }
        
        static boolean detectedCycle(Graph graph) {
            boolean []visited = new boolean[graph.V];
            boolean []onStack = new boolean[graph.V];
            boolean []cycle = {false};
            
            for (int v = 0; v < graph.V; v++) {
                if (!visited[v] ) {
                    dfs(graph, v, visited, onStack, cycle);
                    if (cycle[0])
                        return true;
                }
            }
            return false;
        }
        
        static void dfs(Graph graph, int vertex, boolean []visited, boolean []onStack, boolean []cycle) {
            if (onStack[vertex]) {
                cycle[0] = true;
                return;
            }
            
            visited[vertex] = true;
            onStack[vertex] = true;
            
            for (int w: graph.adj[vertex]) {
                if (cycle[0])
                    return;
                dfs(graph, w, visited, onStack, cycle);
            }
            onStack[vertex] = false;
        }
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for (int []edge: prerequisites)
            graph.addEdge(edge);
        
        // cycle exists then course scheduling is impossible
        return !Graph.detectedCycle(graph);
    }
    
    @Test
    public void example1() {
        Problem630 sol = new Problem630();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        assertThat(sol.canFinish(numCourses, prerequisites), is(true));
    }
    
    @Test
    public void example2() {
        Problem630 sol = new Problem630();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        assertThat(sol.canFinish(numCourses, prerequisites), is(false));
    }
}
