package idv.cnfang.leetcode;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.*;

import org.junit.jupiter.api.Test;



/**
Leetcode <Problem 705> Design HashSet

Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:
MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)


Note:
All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
*/

public class Problem705{
    private LinkedList<Integer> []mylist;
    
    private static int bucketSize = 256;
    
    /** Initialize your data structure here. */
    public Problem705() {
        mylist = new LinkedList[bucketSize];
    }
    
    public void add(int key) {
        int idx = getBucketIdx(key);
        if (mylist[idx] == null)
            mylist[idx] = new LinkedList<>();
        if (!mylist[idx].contains(key))
            mylist[idx].add(key);
        return;
    }
    
    public void remove(int key) {
        int idx = getBucketIdx(key);
        if (mylist[idx] == null)
           return;
        mylist[idx].removeFirstOccurrence(key);
        return;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = getBucketIdx(key);
        if (mylist[idx] == null)
           return false;
        return mylist[idx].contains(key);
    }
    
    private int getBucketIdx(int key) {
        return key % bucketSize;
    }
    
    
    @Test
    public void test() {
        Problem705 hashSet = new Problem705();
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.contains(1));
        assertFalse(hashSet.contains(3));
        hashSet.add(2);
        assertTrue(hashSet.contains(2));
        hashSet.remove(2);
        assertFalse(hashSet.contains(2));
    }
}