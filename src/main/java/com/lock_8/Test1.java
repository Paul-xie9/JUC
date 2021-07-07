package com.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是关于锁的 8个问题
 *1。标准状态下是先发短信还是先打电话？
 *2.sendSms延迟3秒，两个线程是先发短信还是先打电话？
 */
public class Test1 {
    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSms();//发短信
        },"A").start();

        /* 休眠一会儿 */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();   //打电话
        },"B").start();
    }
}


/* 资源类 */
class Phone{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的统一把锁，谁先拿到谁先调用
    public synchronized void sendSms(){
        /* 休眠一会儿 */
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信...");
    }
    public synchronized void call(){
        System.out.println("打电话...");
    }
}