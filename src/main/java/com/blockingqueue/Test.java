package com.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class Test {
    public static void main(String[] args) throws Exception {
       // test1();
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1(){
        //队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
       // System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());//查看队首元素
        System.out.println("================================================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
      //  System.out.println(blockingQueue.remove());
    }

    /**
     * 不抛出异常
     * offer() 放
     * poll() 取
     */
    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));   //false
        System.out.println(blockingQueue.peek());//检测队首元素
        System.out.println("==================================================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null

    }

    /***
     * 等待。一直阻塞
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");//等待。一直阻塞

        System.out.println("======================================================");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());//等待，一直阻塞

    }


    /**
     * 超时等待
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",3, TimeUnit.SECONDS));
        System.out.println("===================================================");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll(2,TimeUnit.SECONDS);
    }
}
