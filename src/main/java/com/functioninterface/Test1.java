package com.functioninterface;

import java.util.function.Function;

public class Test1 {
    public static void main(String[] args) {
        /*Function<String,Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 1024;
            }
        };*/
        Function<String,Integer> function = (s)->{return 1024;};

        System.out.println(function.apply("s"));
    }
}
