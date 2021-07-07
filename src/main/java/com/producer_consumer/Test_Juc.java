package com.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *class BoundedBuffer {
 *    final Lock lock = new ReentrantLock();
 *    final Condition notFull  = lock.newCondition();
 *    final Condition notEmpty = lock.newCondition();
 *
 *    final Object[] items = new Object[100];
 *    int putptr, takeptr, count;
 *
 *    public void put(Object x) throws InterruptedException {
 *      lock.lock(); try {
 *        while (count == items.length)
 *          notFull.await();
 *        items[putptr] = x;
 *        if (++putptr == items.length) putptr = 0;
 *        ++count;
 *        notEmpty.signal();
 *      } finally { lock.unlock(); }
 *    }
 *
 *    public Object take() throws InterruptedException {
 *      lock.lock(); try {
 *        while (count == 0)
 *          notEmpty.await();
 *        Object x = items[takeptr];
 *        if (++takeptr == items.length) takeptr = 0;
 *        --count;
 *        notFull.signal();
 *        return x;
 *      } finally { lock.unlock(); }
 *    }
 *  }
 * （ ArrayBlockingQueue类提供此功能，因此没有理由实现此示例使用类。）
 * Condition实现可以提供Object监视器方法的行为和语义，例如有保证的通知顺序，或者在执行通知时不需要锁定。 如果一个实现提供了这样的专门的语义，那么实现必须记录这些语义。
 *
 * 需要注意的是Condition实例只是普通的对象，其本身作为一个目标synchronized语句，可以有自己的监视器wait和notification个方法调用。 获取Condition实例的监视器锁或使用其监视方法与获取与该Condition相关联的Condition或使用其waiting和signalling方法没有特定关系。 建议为避免混淆，您永远不会以这种方式使用Condition实例，除了可能在自己的实现之内。
 *
 * 除非另有说明，传递任何参数的null值将导致NullPointerException被抛出。
 *
 */

public class Test_Juc {
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
class Date01{
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //执行 +1
    public synchronized void increment() throws InterruptedException {
        lock.lock();//加锁
        try {
            while (number !=0){
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我+1完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    //执行 -1
    public synchronized void decrement() throws InterruptedException {
        lock.lock();      //加锁
      try {
        while (number == 0){
            //等待
        condition.await();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完毕
        condition.signalAll();
    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
/*
* 问题：不能安装ABCD这样的顺序执行
* */
