package idv.cnfang.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
Leetcode <Problem 797>   All Paths From Source to Target

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

==================== Solution ===================
DFS to search valid path and backtrack if proposed path isn't valid.

*/

public class Problem797 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        dfs(graph, ans, path, 0);
        
        return ans;
    }
    
    private void dfs(int [][]graph, List<List<Integer>> ans, 
                     LinkedList<Integer> path, int rootIdx) {
        if (rootIdx == graph.length-1) {
            path.add(rootIdx);
            ans.add(new LinkedList<Integer>(path));
            path.removeLast();
            return;
        }
        if (graph[rootIdx] == null || graph[rootIdx].length == 0)
            return;
        
        path.add(rootIdx);
        for (int nextIdx: graph[rootIdx])
            dfs(graph, ans, path, nextIdx);
        path.removeLast();
        return;
    }
    
    @Test
    public void test() {
        int [][]graph = {{1,2},{3},{3},{}};
        Problem797 sol = new Problem797();
        
        assertThat(sol.allPathsSourceTarget(graph))
            .contains(Arrays.asList(0, 1, 3))
            .contains(Arrays.asList(0, 2, 3));
    }
    
}
