package com.unsafe;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        /**
         * 同理可证
         *  1.List<String> list = new Vector<>();
         *2.List<String> list = Collections.synchronizedList(new ArrayList<>())
         * 3.List<String> list = new CopyOnWriteArrayList<>();
         */
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
