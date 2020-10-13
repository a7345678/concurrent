package com.bingqp.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调中执行耗时操作...");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return 100;
        });
        completableFuture = completableFuture.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("在回调的回调中执行耗时操作...");
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return i + 100;
            });
        });

      completableFuture = completableFuture.thenCompose(i -> {
        return CompletableFuture.supplyAsync(() -> {
          System.out.println("在回调的回调中执行耗时操作...");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return i + 200;
        });
      });

        completableFuture.whenComplete((result, e) -> {
            System.out.println("计算结果:" + result);
        });
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");
        new CountDownLatch(1).await();
    }
}
