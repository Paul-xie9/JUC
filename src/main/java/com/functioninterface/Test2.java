package com.functioninterface;

import java.util.function.Predicate;

/**
 * 断定型接口，输入一个参数，返回值只能是一个布尔值
 */
public class Test2 {
    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>(){
            //判断字符串是否为空！
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        }; */
        Predicate<String> predicate =(s)->{ return s.isEmpty();};

                System.out.println(predicate.test("1"));
    }
}
