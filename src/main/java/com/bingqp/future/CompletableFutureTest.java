package com.bingqp.future;

import com.bingqp.utils.DateUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始:" + DateUtils.getDateString());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始耗时计算:" + DateUtils.getDateString());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束耗时计算:" + DateUtils.getDateString());
            return 100;
        });
        completableFuture.whenComplete((result, e) -> {
            System.out.println("回调结果:" + result);
        });
        System.out.println("结束:" + DateUtils.getDateString());
        new CountDownLatch(1).await();
    }
}
