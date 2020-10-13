package com.bingqp.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    Future<Integer> f = es.submit(() -> {
      Thread.sleep(1000);
      // 结果
      return 100;
    });

    // do something

    Integer result = null;
    try {
      result = f.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(result);

//        while (f.isDone()) {
//            System.out.println(result);
//        }

    System.exit(1);
  }
}
