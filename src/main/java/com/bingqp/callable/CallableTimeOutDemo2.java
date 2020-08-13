package com.bingqp.callable;

import java.util.concurrent.*;
 
public class CallableTimeOutDemo2 {
    public static void  main(String[] args){
        String result = null;
        TaskThread task = new TaskThread();   //实现Callable接口的任务线程类
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //对task对象进行各种set操作以初始化任务
        Future<String> future = executor.submit(task);
        try {
            result = future.get(300, TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException | TimeoutException | ExecutionException e){
            System.out.println("ERROR");
        }
        finally {
            executor.shutdownNow();
        }
        System.out.println(result);
    }
}

class TaskThread implements Callable{
    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        return "Hello";
    }
}