package com.bingqp.future;

import com.bingqp.utils.DateUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始:" + DateUtils.getDateString());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("开始耗时计算:" + DateUtils.getDateString());
                Thread.sleep(10000);
                System.out.println("结束耗时计算:" + DateUtils.getDateString());
                return 100;
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("调用成功");
            }
        }, executorService);
        System.out.println("结束:" + DateUtils.getDateString());
        new CountDownLatch(1).await();
    }
}
