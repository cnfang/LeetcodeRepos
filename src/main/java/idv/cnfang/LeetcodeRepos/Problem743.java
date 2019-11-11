package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 743> Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

=========================================
Apply Dijkstra's algorithm given nonnegative digraph
*/



public class Problem743 {
    class Edge {
        int start;
        int end;
        int weight;
        public Edge(int s, int e, int w) {
            start = s;
            end = e;
            weight = w;
        }
    }
    class Node{
        int ID;
        int distance;
        public Node(int id, int dis) {
            ID = id;
            distance = dis;
        } 
    }
   
    private int longestPathStartFrom(int numVertice, LinkedList<Edge> []adj, int start) {
        // for tracing back the startpoint to any ending point
        Edge []edgeTo = new Edge [numVertice];
        
        int []distTo  = new int [numVertice];
        boolean []visited = new boolean[numVertice];
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.distance-b.distance);
        
        for (int i = 0; i < numVertice; i++)
            distTo[i] = Integer.MAX_VALUE;
        distTo[start] = 0;
        pq.add(new Node(start, distTo[start]));
        
        while (!pq.isEmpty()) {
            Node node = pq.remove();
            visited[node.ID] = true;
            if (adj[node.ID] == null || adj[node.ID].size() == 0) continue;
            Iterator<Edge> iterator = adj[node.ID].iterator();
            while (iterator.hasNext()) {
                Edge e = iterator.next();
                relax(distTo, edgeTo, pq, visited, e);
            }
        }
        
        // find the maximum distance traveled from start
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < numVertice; i++)
            if (distTo[i] > max) max = distTo[i];
        
        return max == Integer.MAX_VALUE? -1: max;
     }
    
    private void relax(int []distTo, Edge []edgeTo, PriorityQueue<Node> pq, boolean []visited, Edge e) {
        
        if (visited[e.end]) return;
        
        if (distTo[e.end] > distTo[e.start] + e.weight) {
            distTo[e.end] = distTo[e.start] + e.weight;
            edgeTo[e.end] = e;
            pq.add(new Node(e.end, distTo[e.end]));
        }
    }
    
    public int networkDelayTime(int[][] times, int N, int K) {
        LinkedList<Edge> []adj = new LinkedList [N+1];
        for (int i = 0; i < times.length; i++) {
            int start = times[i][0];
            int end = times[i][1];
            int weight = times[i][2];
            if (adj[start] == null) adj[start] = new LinkedList<Edge>();
            adj[start].add(new Edge(start, end, weight));
        }
        
        return longestPathStartFrom(N+1, adj, K);
    }
    
    
    @Test
    public void test_networkDelayTime() {
        Problem743 sol = new Problem743();
        int [][]times = {{2,1,1},{2,3,1},{3,4,1}};
        int N = 4;
        int K = 2;
        int expected = 2;
        assertEquals(expected, sol.networkDelayTime(times, N, K));
    }
}