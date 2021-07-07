package com.functioninterface;

import java.util.function.Supplier;

/**
 * 没有参数，只有返回值
 */
public class Test4 {
    public static void main(String[] args) {
       /* Supplier supplier = new Supplier<String>(){

            @Override
            public String get() {
                return "hello!";
            }
        };*/
        Supplier supplier =()->{return "hello!!!";};

        System.out.println(supplier.get());
    }
}
