package com.synchronous;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketTest_Lock {
    public static void main(String[] args) {
        /*并发：多个线程操作同一个类，把资源丢入线程就行*/
        Ticket02 ticket = new Ticket02();

        /*拉姆达表达式：(参数)->{代码}
         * */
        new Thread(()->{
            for (int i = 0; i < 70; i++) ticket.sale(); },"a").start();

        new Thread(()->{
            for (int i = 0; i < 70; i++) {
                ticket.sale();
            }
        },"b").start();

        new Thread(()->{
            for (int i = 0; i < 70; i++) {
                ticket.sale();
            }
        },"c").start();
    }
}

//资源类   OOP:面向对象
class Ticket02 {
    /*属性，方法*/
    private int number = 100;

    Lock lock = new ReentrantLock();    //源码里面有公平锁和非公平锁，默认使用非公平锁
    /*买票的方式
     * */
    public void sale(){
        lock.lock();    //加锁

        /*ctl+alt+T 调出try/catch */

        try {
            /*业务代码*/
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，还剩： "+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();//解锁
        }
    }
}
