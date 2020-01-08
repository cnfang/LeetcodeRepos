package idv.cnfang.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
Leetcode <Problem 146> LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?


Example:

LRUCache cache = new LRUCache(2);
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

============================ Solution
[Approach 1]: linkedHashMap while overriding "removeEldestEntry" method
[Approach 2]: double linked list with Hashmap storing (key, object address)
Note that deletion in double linked list (Java) takes O(n) even if we hold address of object
To achieve O(1) of object deletion, it's better to implement double linked list ourself.
**/

public class Problem146 {
    class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private final int CAPACITY;
        
        public LRULinkedHashMap(int capacity) {
            super(capacity, 1, true);
            // by passing load factor=1, the number of bucket increases double only when number of element exceeds capacity
            // default load factor=0.75, it means number of bucket increases double when number of element exceeds capacity*0.75
            CAPACITY = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > CAPACITY;
        }
    }
    class LRUCache {

        private LRULinkedHashMap<Integer, Integer> map; 
        
        public LRUCache(int capacity) {
            map = new LRULinkedHashMap<>(capacity);
        }
        
        public int get(int key) {
            Integer value = map.get(key);
            return value == null ? -1: value;
        }
        
        public void put(int key, int value) {
            map.put(key, value);
        }
    }
    
    @Test
    public void test_leetcode() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));       // returns 1
        cache.put(3, 3);                     // evicts key 2
        assertEquals(-1, cache.get(2));      // returns -1 (not found)
        cache.put(4, 4);                     // evicts key 1
        assertEquals(-1, cache.get(1));      // returns -1 (not found)
        assertEquals(3, cache.get(3));       // returns 3
        assertEquals(4, cache.get(4));       // returns 4
    }
    
    @Test
    public void givenLinkedHashMap_whenRemovesEldestEntry_thenCorrect() {
        LinkedHashMap<Integer, Integer> map = new LRULinkedHashMap<>(5);
        map.put(1, 2);
        map.put(2, 4);
        map.put(3, 6);
        map.put(4, 8);
        map.put(5, 10);
        
        Set<Integer> keys = map.keySet();
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());
      
        map.put(6, null);
        assertEquals("[2, 3, 4, 5, 6]", keys.toString());
      
        map.put(7, null);
        assertEquals("[3, 4, 5, 6, 7]", keys.toString());
      
        map.put(8, null);
        assertEquals("[4, 5, 6, 7, 8]", keys.toString());
        
        map.get(5);
        assertEquals("[4, 6, 7, 8, 5]",keys.toString());
    }
}

class LRUCache_fromLeetocde {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }
    private int capacity, count;
    private Map<Integer, Node> map;
    private Node head, tail;
    
    public LRUCache_fromLeetocde(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if(null==n){
            return -1;
        }
        update(n);
        return n.value;
    }
    
    public void set(int key, int value) {
        Node n = map.get(key);
        if(null==n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            ++count;
        }
        else{
            n.value = value;
            update(n);
        }
        if(count>capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            --count;
        }
    }
    
    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }
    
    private void remove(Node node){
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}