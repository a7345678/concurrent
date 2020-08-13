package com.bingqp.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class PrintABC {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    ArrayList<Future<Integer>> futureArrayList = new ArrayList<Future<Integer>>();
    for (int i = 0; i < 3; i++) {
      futureArrayList.add(executorService.submit(new CallableTask(i)));
    }
    for (int i = 0; i < 3; i++) {
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

  static class CallableTask implements Callable<Integer> {

    private int index;

    public CallableTask(int index) {
      this.index = index;
    }
    @Override
    public Integer call() throws Exception {
      Thread.sleep(new Random().nextInt() % 1000);
      return index;
    }
  }
}
