package idv.cnfang.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Leetcode <Problem 1116> Print Zero Even Odd
Suppose you are given the following code:

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A will call zero() which should only output 0's.
Thread B will call even() which should only ouput even numbers.
Thread C will call odd() which should only output odd numbers.
Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.

 
Example 1:
Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.


Example 2:
Input: n = 5
Output: "0102030405"
==================================

*/

class ZeroEvenOdd {
    private int n;
    private int curr = 1;
    Semaphore sz;
    Semaphore se;
    Semaphore so;
    
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        sz = new Semaphore(1);
        se = new Semaphore(1);
        so = new Semaphore(1);
        try {
            se.acquire();
            so.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * print zero
     * @throws InterruptedException
     */
    public void zero() throws InterruptedException {
        while (this.curr <= this.n) {
            
            sz.acquire();
            
            if (this.curr > this.n) {
                se.release();
                so.release();
                return;
            }
            
            System.out.print("0");
            
            if ((this.curr & 1) == 1) so.release();
            
            else se.release();
        }
    }

    /**
     * print even number
     * @throws InterruptedException
     */
    public void even() throws InterruptedException {
        
        while (this.curr <= this.n) {
            
            se.acquire();
            
            if (this.curr > this.n) return;
            
            System.out.print(this.curr);
            
            this.curr++;
            
            sz.release();
        }
    }
    
    /**
     * print odd number
     * @throws InterruptedException
     */
    public void odd() throws InterruptedException {
        
        while (this.curr <= this.n) {
            
            so.acquire();
            
            if(this.curr > this.n) return;
            
            System.out.print(this.curr);
            
            this.curr++;
            
            sz.release();
        }
    }
}


public class Problem1116 {

   public static void main(String []args) {
       int n = 13;
       ZeroEvenOdd processor = new ZeroEvenOdd(n);
       
       ExecutorService executor = Executors.newFixedThreadPool(3);
       
       long start = System.currentTimeMillis();
       
       executor.submit(new Runnable() {
        @Override
        public void run() {
            try {
                processor.even();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       });
       
       
       executor.submit(new Runnable() {
           @Override
           public void run() {
               try {
                   processor.odd();
               } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
           }
       });
       
       
       executor.submit(new Runnable() {
           @Override
           public void run() {
               try {
                   processor.zero();
               } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
           }
       });
       
       executor.shutdown();
       
       while (!executor.isTerminated());
       
       long end = System.currentTimeMillis();
       
       System.out.println("\nTime Cost = " + (end-start) + " ms");
   }

}
