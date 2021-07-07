package com.producer_consumer;

/**
 * 线程之间的通信问题，生产者和消费者问题——————————等待唤醒，通知唤醒
 * 线程交替进行  A B 操作同一个资源   number=0
 * A number+1
 * B number-1
 */
public class Test_Old {
    public static void main(String[] args) {
        Date date = new Date();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        /*===================================================================*/
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        /*===================================================================*/
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        /*===================================================================*/
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }


}

/* 资源类 */
/*  判断等待，业务，通知  */
class Date{
    private int number = 0;

    //执行 +1
    public synchronized void increment() throws InterruptedException {
        while (number !=0){
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我+1完毕
        this.notifyAll();
    }

    //执行 -1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0){
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完毕
        this.notifyAll();
    }
}