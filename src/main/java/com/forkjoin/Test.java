package com.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class Test{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // test1();//13668
        //test2();//6326
        test3();//1975
    }

    /**
     * 普通方式
     */
    public static void test1(){

        long start = System.currentTimeMillis();

        Long sum = 0L;
        for (Long i = 0L; i<=10_0000_0000; i++){
            sum +=i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"时间:"+(end-start));
    }

    /**
     * 使用forkjoin
     */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoin(0L,10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"时间:"+(end-start));
    }

    /**
     * 并行流
     * Stream
     */
    public static void test3(){
        long start = System.currentTimeMillis();

        long sum =  LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum= "+sum+"时间:"+(end-start));
    }
}
















/**
 * 求和计算
 *
 */
 class ForkJoin extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long temp = 10000L;

    public ForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 计算方法
     *
     */

    protected Long compute(){
        if ((end-start)<temp){
            Long sum = 0L;
            for (Long i = start; i<=end; i++){
                sum +=i;
            }
            return sum;
        }else{
            long middle = (start+end)/2;
            ForkJoin  test1 = new ForkJoin(start,middle);
            test1.fork();
            ForkJoin  test2 = new ForkJoin(middle,end);
            test2.fork();
           return test1.join()+test2.join();
        }

    }

}
