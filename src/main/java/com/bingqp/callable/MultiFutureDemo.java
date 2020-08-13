package com.bingqp.callable;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class MultiFutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ArrayList<Future<Integer>> futureArrayList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 10; i++) {
            futureArrayList.add(executorService.submit(new CallableTask()));
        }

        for (int i = 0; i < 10; i++) {
            try {
                Integer integer = futureArrayList.get(i).get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    public static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}