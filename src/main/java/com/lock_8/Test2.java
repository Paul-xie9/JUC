package com.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁：就是关于锁的 8个问题
 *3.添加一个普通方法是先打电话还是先发短信？
 * 4.两个对象，两个同步方法，先打电话还是先发短信？
 */
public class Test2 {
    public static void main(String[] args) {

        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();

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
            phone2.call();   //打电话
        },"B").start();
    }
}


/* 资源类 */
class Phone2{
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
    /* 普通方法不受锁的影响 */
    public void hello(){
        System.out.println("hello!");
    }
}