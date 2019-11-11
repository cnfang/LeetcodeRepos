package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.*;
import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 1129> Shortest Path with Alternating Colors

Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).

 

Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]
 

Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
*/

public class Problem1129 {
    class Edge {
        int start;
        int end;
        int color;
        public Edge (int s, int e, int c) {
            start = s;
            end = e;
            color = c;
        }
    }
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        HashSet<Edge> []adj = new HashSet [n];
        // create adj array
        for (int i = 0; i < red_edges.length; i++) {
            Edge e = new Edge(red_edges[i][0], red_edges[i][1], 0);
            if (adj[e.start] == null) adj[e.start] = new HashSet<Edge>();
            adj[e.start].add(e);
        }
        for (int i = 0; i < blue_edges.length; i++) {
            Edge e = new Edge(blue_edges[i][0], blue_edges[i][1], 1);
            if (adj[e.start] == null) adj[e.start] = new HashSet<Edge>();
            adj[e.start].add(e);
        }
        
        int [][]distTo = new int[n][2];
        for (int i = 0; i < n; i++)
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
        
        bfs(adj, distTo);
        
        int []res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(distTo[i][0], distTo[i][1]);
            if (res[i] == Integer.MAX_VALUE)
                res[i] = -1;
        }
        
        return res;
    }
    
    private void bfs(HashSet<Edge> []adj, int [][]distTo) {
        Queue<List<Integer>> queue = new ArrayDeque<List<Integer>>();
        boolean [][]visited = new boolean[adj.length][2];
        // column 0: red, column 1: blue
        queue.add(Arrays.asList(0, 0));
        queue.add(Arrays.asList(0, 1));
        int level = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> node = queue.poll();
                int v = node.get(0);
                int c = node.get(1);
                if (visited[v][c]) continue;
                visited[v][c] = true;
                distTo[v][c] = level;
                if (adj[v] == null) continue;
                
                Iterator<Edge> iterator = adj[v].iterator();
                while (iterator.hasNext()) {
                    Edge e = iterator.next();
                    if (e.color != c && !visited[e.end][e.color]) 
                        queue.add(Arrays.asList(e.end, e.color));
                }
            }
            level++;
        }
        
        return;
    }
    
    @Test
    public void testshortestAlternatingPaths_Case1() {
        Problem1129 sol = new Problem1129();
        int n = 3;
        int [][]red_edges = {{0,1},{1,2}};
        int [][]blue_edges = {};
        int []result = sol.shortestAlternatingPaths(n, red_edges, blue_edges);
        int []expected = {0,1,-1};
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testshortestAlternatingPaths_Case2() {
        Problem1129 sol = new Problem1129();
        int n = 3;
        int [][]red_edges = {{0,1}};
        int [][]blue_edges = {{2,1}};
        int []result = sol.shortestAlternatingPaths(n, red_edges, blue_edges);
        int []expected = {0,1,-1};
        assertArrayEquals(expected, result);
    }
}