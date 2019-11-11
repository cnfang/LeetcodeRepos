package idv.cnfang.leetcode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;


/**
Leetcode <Problem 1195> Fizz Buzz Multithreaded

Write a program that outputs the string representation of numbers from 1 to n, however:

If the number is divisible by 3, output "fizz".
If the number is divisible by 5, output "buzz".
If the number is divisible by both 3 and 5, output "fizzbuzz".
For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.

Suppose you are given the following code:

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:

Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
Thread D will call number() which should only output the numbers.
==================================
Pseudocode: 
            if (divisible by 15) {
                print("fizzbuzz");
                current number + 1
            }
            else if (divisible by 3) {
                print("fizz");
                current number + 1
            }
            else if (divisible by 5) {
                print("buzz");
                current number + 1  
            }
            else {
                print(current number);
                current number + 1
            }

Solution 1: using (Callable & Future & ExecutorService) to manage all threads and get thread's status [Problem1195]
Solution 2: using Runnable [see Problem1195_Runnable]
*/
class FizzBuzz {
    private int n;
    private volatile int curr = 1;
    Semaphore s15;
    Semaphore s3;
    Semaphore s5;
    Semaphore sP;
    
    
    public FizzBuzz(int n) {
        this.n = n;
        s15 = new Semaphore(1);
        s3 = new Semaphore(1);
        s5 = new Semaphore(1);
        sP = new Semaphore(1);
        try {
            s3.acquire();
            s5.acquire();
            sP.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * To shut down remaining thread if one of thread completes their job
     * It's done by releasing all semaphores.
     */
    private void releaseAll() {
        s3.release();
        s5.release();
        s15.release();
        sP.release();
    }

    /**
     * increase current number by 1 and check the stop condition
     */
    private void increment() {
        this.curr++;
        if (this.curr > this.n) 
            releaseAll();
    }
    
    // printFizz.run() outputs "fizz".
    public void fizz() throws InterruptedException {
        while (this.curr <= this.n) {
           
           s3.acquire();
           
           // when current thread returns from blocking mode, it could be the stopping condition fulfilled
           // check again before doing division
           if (this.curr > n) return;
           
           else if (this.curr % 3 == 0) {
               System.out.print("fizz "); // printFizz.run();
               increment();
               s15.release();
           }
           
           else s5.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz() throws InterruptedException {
        while (this.curr <= this.n) {
            
            s5.acquire();
            
            // when current thread returns from blocking mode, it could be the stopping condition fulfilled
            // check again before doing division
            if (this.curr > this.n) return;
            
            else if (this.curr % 5 == 0) {
                System.out.print("buzz "); // printBuzz.run()
                increment();
                s15.release();
            }
            
            else sP.release();
         }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz() throws InterruptedException {
        while (this.curr <= this.n) {
           
            s15.acquire();
            
            // when current thread returns from blocking mode, it could be the stopping condition fulfilled
            // check again before doing division
            if (this.curr > this.n) return;
            
            else if (this.curr % 15 == 0) {
               System.out.print("fizzbuzz ");
               increment();
               s15.release();
            }
            
            else s3.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number() throws InterruptedException {
        while (this.curr <= this.n) {
            
            sP.acquire();
            
            // when current thread returns from blocking mode, it could be the stopping condition fulfilled
            // check again before doing division
            if (this.curr > this.n) return;
            
            System.out.print(this.curr + " "); // printNumber.accept(this.curr);
            
            increment();
            
            s15.release();
         }
    }
}

public class Problem1195 {

    public static void main(String []args) {
        int n = 30;
        FizzBuzz processor = new FizzBuzz(n);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<Boolean> []future = new Future[4];
        
        future[0] = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    processor.buzz();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        });
    
        future[1] = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    processor.fizz();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        });
        
        future[2] = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    processor.fizzbuzz();;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        });
        
        future[3] = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    processor.number();;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        });
        
        executor.shutdown();
        // wait until all thread completes their job
        while (!executor.isTerminated()) {}
        System.out.println("\nAll task completed");
        
        
//        // allow maximum 10 seconds then shutdown all service.
//        try {
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        //System.out.println("\nAll task completed");
    }

}

// using thread 
class Problem1195_Runnable {

    public static void main(String []args) {
        int n = 3;
        FizzBuzz processor = new FizzBuzz(n);
        
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.buzz();
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
                    processor.fizz();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.fizzbuzz();;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.number();;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        // wait until all threads finishing their job
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {}
        
        System.out.println("\n All tasks finished");
    }

}
