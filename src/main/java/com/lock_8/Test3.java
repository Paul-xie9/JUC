package com.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是关于锁的 8个问题
 *5.增加两个静态的同步方法,只有一个对象，先发短信还是打电话？
 *6.增加两个静态的同步方法,两个对象，先发短信还是打电话？
 */
public class Test3 {
    public static void main(String[] args) {
        //两个对象的class模板只有一个，static锁的就是class
        Phone3 phone = new Phone3();
        Phone3 phone3 = new Phone3();


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
            phone3.call();   //打电话
        },"B").start();
    }
}


/* 资源类 */
class Phone3{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的统一把锁，谁先拿到谁先调用
    //static：类一加载就有了，锁的是class
    public static synchronized void sendSms(){
        /* 休眠一会儿 */
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信...");
    }
    public static synchronized void call(){
        System.out.println("打电话...");
    }
}