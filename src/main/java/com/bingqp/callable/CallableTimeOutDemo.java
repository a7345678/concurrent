package com.bingqp.callable;

import java.util.concurrent.*;
 
public class CallableTimeOutDemo {
    public static void main(String[] args) {
        String result = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数
            public String call() {
                //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
                return "Hello";
            }
        });
        executor.execute(future);
        //在这里可以做别的任何事情
        try {
            //取得结果，同时设置超时执行时间为0.5秒。
            // 同样可以用future.get()，不设置执行超时时间取得结果
            result = future.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("TimeOut Error");
            future.cancel(true);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
            future.cancel(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executor.shutdown();
        }
        System.out.println(result);
    }
}