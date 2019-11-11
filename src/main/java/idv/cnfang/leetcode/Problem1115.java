package idv.cnfang.leetcode;

import java.util.concurrent.Semaphore;

/**
Leetcode <Problem 1115> Print FooBar Alternately

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.

Example 1:
Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.


Example 2:
Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.
==================================
using Semaphore
*/

class FooBar {
    private int n;
    private Semaphore sB;
    private Semaphore sF;

    public FooBar(int n) {
        this.n = n;
        sB = new Semaphore(1);
        sF = new Semaphore(1);
        try {
            sB.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void foo() throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            sF.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            System.out.print("foo");
            sB.release();
        }
    }

    public void bar() throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            sB.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            System.out.print("bar");
            sF.release();
        }
    }
}

public class Problem1115 {

    public static void main(String []args) {
        FooBar foobar = new FooBar(3);
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foobar.foo();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foobar.bar();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        System.out.println("\nFinished!!!");
    }

}
