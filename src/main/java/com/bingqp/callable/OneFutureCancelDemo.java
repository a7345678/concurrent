package com.bingqp.callable;

import java.util.Random;
import java.util.concurrent.*;

class OneFutureCancelDemo {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new CallableTask<Integer>());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
    static class CallableTask<I extends Number> implements Callable<Integer> {
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return new Random().nextInt();
        }
    }
}

