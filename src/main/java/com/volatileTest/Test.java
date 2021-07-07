package com.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * 8种操作：
 * lock(锁定)，作用于主内存中的变量，把变量标识为线程独占的状态。
 * read(读取)，作用于主内存的变量，把变量的值从主内存传输到线程的工作内存中，以便下一步的load操作使用。
 * load(加载)，作用于工作内存的变量，把read操作主存的变量放入到工作内存的变量副本中。
 * use(使用)，作用于工作内存的变量，把工作内存中的变量传输到执行引擎，每当虚拟机遇到一个需要使用到变量的值的字节码指令时将会执行这个操作。
 * assign(赋值)，作用于工作内存的变量，它把一个从执行引擎中接受到的值赋值给工作内存的变量副本中，每当虚拟机遇到一个给变量赋值的字节码指令时将会执行这个操作。
 * store(存储)，作用于工作内存的变量，它把一个从工作内存中一个变量的值传送到主内存中，以便后续的write使用。
 * write(写入)：作用于主内存中的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中。
 * unlock(解锁)：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
 */
public class Test {
    private volatile static int number = 0;
    public static void main(String[] args) {

        new Thread(()->{
            while (number==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number = 1;

        System.out.println(number);

    }

}
