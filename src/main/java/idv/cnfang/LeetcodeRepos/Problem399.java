package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.*;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 399> Evaluate Division

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

public class Problem399 {
    class Edge {
        int start;
        int end;
        double weight;
        public Edge(int s, int e, double w) {
            start = s;
            end = e;
            weight = w;
        }
    }
    
    class Digraph {
        HashMap<String, Integer> lookup;
        HashMap<Integer, LinkedList<Edge>> adj;
        int numVertice;
        
        public Digraph (){
            lookup = new HashMap<String, Integer>();
            adj = new HashMap<Integer, LinkedList<Edge>>();
            numVertice = 0;
        }
        
        public void addEdge(List<String> edge, double val) {
            String end = edge.get(0);
            String start = edge.get(1);
            
            if (!lookup.containsKey(start)) {
                lookup.put(start, numVertice++);
                int id = lookup.get(start);
                adj.put(id, new LinkedList<Edge>());
            }
            if (!lookup.containsKey(end)) {
                lookup.put(end, numVertice++);
                int id = lookup.get(end);
                adj.put(id, new LinkedList<Edge>());
            }
            
            int idStart = lookup.get(start);
            int idEnd = lookup.get(end);
            adj.get(idStart).add(new Edge(idStart, idEnd, val));
            adj.get(idEnd).add(new Edge(idEnd, idStart, 1.0/val));
        }
        
        public double searchPath(String start, String end) {
            if (!lookup.containsKey(start) || !lookup.containsKey(end)) return -1.0;
            if (start == end) return 1.0;
            
            int idStart = lookup.get(start);
            int idEnd = lookup.get(end);
            Edge []pathTo = new Edge[numVertice];
            boolean []visited = new boolean[numVertice];
            

            if (!dfs(pathTo, visited, idStart, idEnd)) return -1.0;
            
            double res = 1;
            for (Edge e = pathTo[idEnd]; e != null; e = pathTo[e.start])
                res *= e.weight;
            
            return res;
        }
        
        private boolean dfs(Edge []pathTo, boolean []visited, int curr,int target) {
            if (curr == target) return true;
            
            LinkedList<Edge> head = adj.get(curr);
            if (head == null || head.size() == 0) return false;
            
            visited[curr] = true;
            Iterator<Edge> iterator = head.iterator();
            while (iterator.hasNext()) {
                Edge next = iterator.next();
                if (visited[next.end]) continue;
                
                Edge ori = pathTo[next.end];
                pathTo[next.end] = next;
                if (dfs(pathTo, visited, next.end, target)) return true;
                pathTo[next.end] = ori;
            }
            
            visited[curr] = false;
            return false;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Digraph myDigraph = new Digraph();
        for (int i = 0; i < values.length; i++)
            myDigraph.addEdge(equations.get(i), values[i]);
        
        double []res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)
            res[i] = myDigraph.searchPath(queries.get(i).get(1), queries.get(i).get(0));
        
        return res;
    }
    
    @Test
    public void test_calcEquation() {
        Problem399 sol = new Problem399();
        List<List<String>> equations = new ArrayList<List<String>>();
        equations.add(Arrays.asList("x1", "x2"));
        equations.add(Arrays.asList("x2", "x3"));
        equations.add(Arrays.asList("x3", "x4"));
        equations.add(Arrays.asList("x4", "x5"));
        
        double []values = {3.0, 4.0, 5.0, 6.0};
        
        List<List<String>> queries = new ArrayList<List<String>>();
        queries.add(Arrays.asList("x1", "x5"));
        queries.add(Arrays.asList("x5", "x2"));
        queries.add(Arrays.asList("x2", "x4"));
        queries.add(Arrays.asList("x2", "x2"));
        queries.add(Arrays.asList("x2", "x9"));
        queries.add(Arrays.asList("x9", "x9"));
        
        double []result = sol.calcEquation(equations, values, queries);
        double []expected = {360.0, 0.00833, 20.0, 1.0, -1.0, -1.0};
        double tolerance = 0.01;
        assertArrayEquals(expected, result, tolerance);
    }
}