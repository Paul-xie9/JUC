package com.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是关于锁的 8个问题
 *7.一个静态一个普通同步方法，一个对象，先打电话还是先发短信？
 * 8.一个静态一个普通同步方法，两个个对象，先打电话还是先发短信？
 */
public class Test4 {
    public static void main(String[] args) {

        Phone4 phone = new Phone4();
        Phone4 phone4 = new Phone4();
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
            phone4.call();   //打电话
        },"B").start();
    }
}


/* 资源类 */
class Phone4{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的统一把锁，谁先拿到谁先调用

    //锁的是class模板
    public static synchronized void sendSms(){
        /* 休眠一会儿 */
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信...");
    }

    //锁的是调用者
    public synchronized void call(){
        System.out.println("打电话...");
    }
}