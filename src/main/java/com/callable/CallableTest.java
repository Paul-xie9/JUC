package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
       MyTread myTread = new MyTread();
        FutureTask futureTask = new FutureTask(myTread);    //适配类
        new Thread(futureTask,"A").start();
    }
}

class MyTread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
