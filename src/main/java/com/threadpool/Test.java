package com.threadpool;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class Test {
    public static void main(String[] args) {
        //3 大方法
        //ExecutorService  threadpool = Executors.newSingleThreadExecutor();//单个线程
           //ExecutorService  threadpool = Executors.newFixedThreadPool(5);//创建一个固定的线程池
      //  ExecutorService  threadpool = Executors.newCachedThreadPool();//创建一个可伸缩的线程池


        /**
         * cup密集型：几核，第二个参数就是几
         * io密集型： >判断你程序中十分耗io的线程 ；设置为其两倍
         */
        ExecutorService threadpool = new ThreadPoolExecutor (
                2,  //7大参数
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy());//拒绝策略； 4种策略
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 0; i < 10; i++) {
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"=>go!");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //程序结束，关闭线程池
            threadpool.shutdownNow();
        }
    }
}
