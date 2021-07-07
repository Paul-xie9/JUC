package com.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠，召唤神龙
         * 召唤龙珠的线程
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功！");
        });
        for (int i = 0; i < 7; i++) {
            final int temp=i;

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了"+temp+"个龙珠。");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
