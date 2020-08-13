package com.bingqp.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Print implements Runnable {

  private int index = 0;
  private ReentrantLock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    lock.lock();
    try {
      for (int i = 0; i < 1; i++) {
        if (name.equals("A")) {
          while (index % 3 != 0) {
            condition.await();
          }
        } else if (name.equals("B")) {
          while (index % 3 != 1) {
            condition.await();
          }
        } else if (name.equals("C")) {
          while (index % 3 != 2) {
            condition.await();
          }
        }
        index++;
        System.out.println(name);
        condition.signalAll();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    Print task = new Print();
    Thread threadA = new Thread(task,"A");
    Thread threadB = new Thread(task,"B");
    Thread threadC = new Thread(task,"C");
    threadC.start();
    threadB.start();
    threadA.start();
  }
}
