package com.bingqp.future;

import com.bingqp.utils.DateUtils;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

// https://www.cnblogs.com/weknow619/p/10873519.html
public class NettyFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        System.out.println("开始:" + DateUtils.getDateString());

        Future<Integer> f = group.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("开始耗时计算:" + DateUtils.getDateString());
                Thread.sleep(10000);
                System.out.println("结束耗时计算:" + DateUtils.getDateString());
                return 100;
            }
        });

        f.addListener(new FutureListener<Object>() {
            @Override
            public void operationComplete(Future<Object> objectFuture) throws Exception {
                System.out.println("计算结果:" + objectFuture.get());
            }
        });

        System.out.println("结束:" + DateUtils.getDateString());
        // 不让守护线程退出
        new CountDownLatch(1).await();
    }
}
