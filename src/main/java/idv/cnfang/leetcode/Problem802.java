package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 802> Find Eventual Safe States

In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
*/
// slow: 34ms faster than 33.83% submission, 62.6MB faster than 100% submission
public class Problem802 {
    boolean[] circle;
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int numVertice = graph.length;
        
        boolean []visited = new boolean[numVertice];
        HashSet<Integer> recBag = new HashSet<Integer>();
        circle = new boolean[numVertice];
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        for (int i = 0; i < numVertice; i++) {
            if (visited[i]) continue;
            if (dfs(graph, visited, i, recBag)) {
                addToCircle(recBag);
                recBag.clear();
            }
        }
        
        for (int i = 0; i < numVertice; i++)
            if (!circle[i]) ans.add(i);
        return ans;
    }
    
    /**
     * add the current circle to circle set
     * @param recBag
     */
    private void addToCircle(HashSet<Integer> recBag) {
        for (int w: recBag) circle[w] = true;
        return;
    }
    
    /**
     * return true if found a circle, false if not
     * @param adj
     * @param visited
     * @param root
     * @param recBag
     * @return
     */
    private boolean dfs(int [][]adj, boolean []visited, int root, HashSet<Integer> recBag) {   
        if (recBag.contains(root) || circle[root]) return true;
     
        if (visited[root]) return false;
        
        recBag.add(root);
        visited[root] = true;
        
        for (int w: adj[root]) {
            if (dfs(adj, visited, w, recBag)) return true;
        }
        
        recBag.remove(root);
        return false;
    }
    
    @Test
    public void test_eventualSafeNodes() {
        Problem802 sol = new Problem802();
        int [][]graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> ans = sol.eventualSafeNodes(graph);
        Set<Integer> result = new HashSet<>(ans);
        
        Set<Integer> expected = new HashSet<>(Arrays.asList(2,4,5,6));
        assertEquals(expected, result);
    }
}

// HashSet Implementation: 68ms faster than 28% submission, 64.3MB less than 100% submission
//public class Problem802 {
//    HashSet<Integer> circle;
//    public Problem802 (){
//        circle = new HashSet<Integer>();
//    }
//    public List<Integer> eventualSafeNodes(int[][] graph) {
//        int numVertice = graph.length;
//        int [][]adj = graph;
//        
//        boolean []visited = new boolean[numVertice];
//        HashSet<Integer> visitedBag = new HashSet<Integer>();
//        HashSet<Integer> tmpCircle = new HashSet<Integer>();
//        
//        for (int i = 0; i < numVertice; i++) {
//            if (visited[i]) continue;
//            dfs(adj, visited, i, visitedBag, tmpCircle);
//            if (tmpCircle.size() > 0) circle.addAll(tmpCircle);
//            tmpCircle.clear();
//            visitedBag.clear();
//        }
//        
//        ArrayList<Integer> ans = new ArrayList<Integer>();
//        for (int i = 0; i < numVertice; i++)
//            if (!circle.contains(i)) ans.add(i);
//        
//        return ans;
//    }
//    
//    private void dfs(int [][]adj, boolean []visited, int root, HashSet<Integer> visitedBag, HashSet<Integer> tmpCircle) {   
//        if (visitedBag.contains(root) || circle.contains(root)) {
//            visitedBag.add(root);
//            tmpCircle.addAll(visitedBag);
//            return;
//        }
//     
//        if (visited[root]) return;
//        
//        visitedBag.add(root);
//        visited[root] = true;
//        
//        
//        for (int w: adj[root]) {
//            if (tmpCircle.size() > 0) return;
//            dfs(adj, visited, w, visitedBag, tmpCircle);
//        }
//        
//        visitedBag.remove(root);
//        return;
//    }
//    
//    public static void main(String []args) {
//        Problem802 sol = new Problem802();
//        int [][]graph = {{1,2},{2,3},{5},{0},{5},{},{}};
//        List<Integer> ans = sol.eventualSafeNodes(graph);
//        System.out.println(ans);
//    }
//}