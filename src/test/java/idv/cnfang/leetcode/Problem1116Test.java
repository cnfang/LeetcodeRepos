package idv.cnfang.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class Problem1116Test {

    @Test
    void test_ZeroEvenOdd() {
        int n = 13;
        
        ZeroEvenOdd processor = new ZeroEvenOdd(n);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
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
    }
}
