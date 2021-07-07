package com.add;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 减法计数器
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"  Go out!");
                countDownLatch.countDown();//数量减一
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//等待计数器归零再向下继续

        System.out.println("Close door!");
    }

}
