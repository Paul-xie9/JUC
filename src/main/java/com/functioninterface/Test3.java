package com.functioninterface;

import java.util.function.Consumer;

/**
 * 只有输入，没有返回值
 */
public class Test3 {
    public static void main(String[] args) {
       /*Consumer<String> consumer =  new Consumer<String>(){

           @Override
           public void accept(String o) {
               System.out.println( o );
           }
       };*/
        Consumer<String> consumer =(o)->{
            System.out.println(o);
        };
                consumer.accept("a");

    }
}
