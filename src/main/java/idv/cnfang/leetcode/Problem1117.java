package idv.cnfang.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Leetcode <Problem 1117> Building H2O
There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.

Example 1:
Input: "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.


Example 2:
Input: "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
 

Constraints:

Total length of input string will be 3n, where 1 ≤ n ≤ 20.
Total number of H will be 2n in the input string.
Total number of O will be n in the input string.
==================================

*/

class H2O {

    Semaphore sh = new Semaphore(2);
    Semaphore so = new Semaphore(1);
    
    public H2O() {
        try {
            so.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void hydrogen() throws InterruptedException {
        sh.acquire();
        System.out.print("H");
        if (sh.availablePermits() == 0) so.release();
    }

    public void oxygen() throws InterruptedException {
       so.acquire();
       System.out.print("O");
       sh.release(2);
    }
}

public class Problem1117 {

    public static void main(String[] args) {
        H2O module = new H2O();
        
        String input = "HHHHHHOOO";
        
        ExecutorService executor = Executors.newFixedThreadPool(input.length());
        
        long start = System.currentTimeMillis();
        
        // register all the tasks
        for (char c: input.toCharArray()) {
            switch (c) {
            case 'H':
                submitHydrogenTask(executor, module);
                break;
            case 'O':
                submitOxygenTask(executor, module);
                break;
            }
        }
        
        executor.shutdown();
        
        while (!executor.isTerminated());
        
        long end = System.currentTimeMillis();
        
        System.out.println("\nTime Cost = " + (end-start) + " ms");

    }
    
    private static void submitHydrogenTask(ExecutorService executor, H2O module) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    module.hydrogen();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
           });
    }
    
    private static void submitOxygenTask(ExecutorService executor, H2O module) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    module.oxygen();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
           });
    }

}
