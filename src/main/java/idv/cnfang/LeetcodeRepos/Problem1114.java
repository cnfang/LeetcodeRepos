package idv.cnfang.leetcode;

import java.util.concurrent.Semaphore;


/**
Leetcode <Problem 1114> Print in Order

Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
 

Example 1:
Input: [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.


Example 2:
Input: [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
 

Note:

We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seems to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.

==================================
using Semaphore to control thread access
*/
class Foo {
    Semaphore s1;
    Semaphore s2;

    public Foo() {
        s1 = new Semaphore(1);
        s2 = new Semaphore(1);
        
        try {
            s1.acquire();
            s2.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void first() throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        System.out.print("1");
        s1.release();
    }

    public void second() throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        s1.acquire();
        System.out.print("2");
        s2.release();
    }

    public void third() throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
       s2.acquire();
       System.out.print("3");
       s2.release();
    }
}

public class Problem1114 {

    public static void main(String[] args) {
        Foo foo = new Foo();
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    foo.first();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
        
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    foo.second();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
        
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    foo.third();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
        t3.start();
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("All Finish.");

    }

}
