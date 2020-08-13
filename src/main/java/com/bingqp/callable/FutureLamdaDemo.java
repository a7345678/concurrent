package com.bingqp.callable;

import java.util.Random;
import java.util.concurrent.*;

public class FutureLamdaDemo {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Callable<Integer> callable=()->{
          Thread.sleep(3000);
          return new Random().nextInt();
        };
        Future<Integer> future = executorService.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}