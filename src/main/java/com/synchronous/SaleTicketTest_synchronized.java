package com.synchronous;

/*
* 公司开发需要降低耦合性
* 线程就是一个资源，没有任何的操作
*
* */
public class SaleTicketTest_synchronized {
    public static void main(String[] args) {
        /*并发：多个线程操作同一个类，把资源丢入线程就行*/
        Ticket01 ticket = new Ticket01();

        /*拉姆达表达式：(参数)->{代码}
        * */
         new Thread(()->{
             for (int i = 0; i < 70; i++) {
                 ticket.sale();
             }
         },"a").start();

        new Thread(()->{
            for (int i = 0; i < 70; i++) {
                ticket.sale();
            }
        },"b").start();

        new Thread(()->{
            for (int i = 0; i < 70; i++) {
                ticket.sale();
            }
        },"c").start();
    }
}

//资源类   OOP:面向对象
class Ticket01 {
    /*属性，方法*/
    private int number = 100;
    /*买票的方式
    * synchronized:本质就是列队排队
    * */
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票，还剩： "+number);
        }
    }
}