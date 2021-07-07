package com.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 目的：使多个线程顺序执行
 */
public class Test_Juc_2 {
    public static void main(String[] args) {
        Date2 date = new Date2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.printC();
            }
        },"C").start();
    }
}

/* 资源类 */
class Date2{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; //1:A  2:B  3:C

    public void printA(){
        lock.lock();    //开锁
        try {
            /* 业务 ：判断 -> 执行 -> 通知*/
            while(number !=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"——>现在是A线程");
            /* 通过自己定义的信号，唤醒指定的线程 */
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printB(){lock.lock();    //开锁
        try {
            /* 业务 ：判断 -> 执行 -> 通知*/
            while(number !=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"——>现在是B线程");
            /* 通过自己定义的信号，唤醒指定的线程 */
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }}
    public void printC(){lock.lock();    //开锁
        try {
            /* 业务 ：判断 -> 执行 -> 通知*/
            while(number !=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"——>现在是C线程");
            /* 通过自己定义的信号，唤醒指定的线程 */
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }}
}