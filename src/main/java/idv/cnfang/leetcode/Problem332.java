package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


/**
Leetcode <Problem 332> Reconstruct Itinerary

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]


Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/


public class Problem332{
    
    class listComparator implements Comparator<List<String>> {
        public int compare(List<String> m1, List<String> m2) {
            int var = m1.get(0).compareTo(m2.get(0));
            if (var != 0) return var;
            var = m1.get(1).compareTo(m2.get(1));
            return var;
        }
    }
    
//    static String concat(List<String> item) {
//        //return item.stream().reduce("", (r, i) -> r+i);
//        return String.join("", item);
//    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        // return method1(tickets); 
        return method2(tickets);
    }
    
    private List<String> method2(List<List<String>> tickets) {
        tickets.sort((o1, o2) -> String.join("", o1).compareTo(String.join("", o2)));
        Map<String, Queue<List<String>>> edges = new HashMap<String, Queue<List<String>>>();
        tickets.forEach(ticket -> {
            String start = ticket.get(0);
            String end = ticket.get(1);
            edges.putIfAbsent(start, new ArrayDeque<>());
            edges.get(start).add(ticket);
            
            edges.putIfAbsent(end, new ArrayDeque<>());
        });
        
        LinkedList<String> path = new LinkedList<>();
        dfs2(edges, tickets.size() + 1, path, "JFK");
        return path;
    }
    
    private boolean dfs2(Map<String, Queue<List<String>>> edges, 
                         int placeNum,
                         LinkedList<String> path,
                         String start) {
        
        path.add(start);
        
        if (path.size() == placeNum)
            return true;
        
        int num = edges.get(start).size();
        for (int i = 0; i < num; i++) {
            List<String> item = edges.get(start).remove();
            if (dfs2(edges, placeNum, path, item.get(1)))
                return true;
            edges.get(start).add(item);
        }
        
        path.removeLast();
        return false;
    }
    
    
    private List<String> method1(List<List<String>> tickets) {
        tickets.sort(new listComparator());
        HashMap<String, ArrayList<String>> lookup = new HashMap<String, ArrayList<String>>();
        HashMap<String, Integer> availableTicket = new HashMap<String, Integer>();
        
        for (int i = 0; i < tickets.size(); i++) {
            String start = tickets.get(i).get(0);
            String end = tickets.get(i).get(1);
            lookup.putIfAbsent(start, new ArrayList<String>());
            lookup.putIfAbsent(end, new ArrayList<String>());
            
            ArrayList<String> head = lookup.get(start);
            head.add(end);
            
            String key = start+end;
            availableTicket.putIfAbsent(key, 0);
            int val = availableTicket.get(key)+1;
            availableTicket.put(key, val);
        }
        
        
        List<String> ans = new ArrayList<String>();
        ans.add("JFK");
        dfs(ans, lookup, availableTicket, "JFK", tickets.size()+1);
        
        return ans;
    }
    
    private boolean dfs(List<String> ans, HashMap<String, ArrayList<String>> lookup, HashMap<String, Integer> availableTicket, String key, int target) {
        if (ans.size() == target) return true;
        
        ArrayList<String> head = lookup.get(key);
        
        if (head.size() == 0) return false;
        
        for (int i = 0; i < head.size(); i++){
            String next = head.get(i);
            ans.add(next);
            String comb = key + next;
            int remain = availableTicket.get(comb) - 1;
            availableTicket.put(comb, remain);
            if (remain >= 0) {
                if (dfs(ans, lookup, availableTicket, next, target)) return true;
            }
            availableTicket.put(comb, remain+1);
            ans.remove(ans.size()-1);
        }
        
        return false;
    }

    @Test
    public void example1() {
        List<List<String>> tickets = new ArrayList<List<String>>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));
        Problem332 sol = new Problem332();
        List<String> result = sol.findItinerary(tickets);
        List<String> expected = Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC");
        assertEquals(expected, result);
    }
    
    @Test
    public void example2() {
        List<List<String>> tickets = new ArrayList<List<String>>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        
        Problem332 sol = new Problem332();
        List<String> result = sol.findItinerary(tickets);
        List<String> expected = Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO");
        assertEquals(expected, result);
    }
    
    @Test
    public void example3() {
        List<List<String>> tickets = new ArrayList<List<String>>();
        tickets.add(Arrays.asList("EZE","TIA"));
        tickets.add(Arrays.asList("EZE","HBA"));
        tickets.add(Arrays.asList("AXA","TIA"));
        tickets.add(Arrays.asList("JFK","AXA"));
        tickets.add(Arrays.asList("ANU","JFK"));
        
        tickets.add(Arrays.asList("ADL","ANU"));
        tickets.add(Arrays.asList("TIA","AUA"));
        tickets.add(Arrays.asList("ANU","AUA"));
        tickets.add(Arrays.asList("ADL","EZE"));
        tickets.add(Arrays.asList("ADL","EZE"));
        
        tickets.add(Arrays.asList("EZE","ADL"));
        tickets.add(Arrays.asList("AXA","EZE"));
        tickets.add(Arrays.asList("AUA","AXA"));
        tickets.add(Arrays.asList("JFK","AXA"));
        tickets.add(Arrays.asList("AXA","AUA"));
        
        tickets.add(Arrays.asList("AUA","ADL"));
        tickets.add(Arrays.asList("ANU","EZE"));
        tickets.add(Arrays.asList("TIA","ADL"));
        tickets.add(Arrays.asList("EZE","ANU"));
        tickets.add(Arrays.asList("AUA","ANU"));
        
        Problem332 sol = new Problem332();
        List<String> result = sol.findItinerary(tickets);
        List<String> expected = Arrays.asList("JFK", "AXA", "AUA", "ADL", "ANU", 
                                              "AUA", "ANU", "EZE", "ADL", "EZE", 
                                              "ANU", "JFK", "AXA", "EZE", "TIA", 
                                              "AUA", "AXA", "TIA", "ADL", "EZE", 
                                              "HBA");
        assertEquals(expected, result);
    }
}