package com.unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发下 ArrayList不安全
 *
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         * 解决方案：
         *  1.List<String> list = new Vector<>();
         *2.List<String> list = Collections.synchronizedList(new ArrayList<>())
         * 3.List<String> list = new CopyOnWriteArrayList<>();
         * CopyOnWrite: 写入时复制 COW 计算机设计领域的一种优化策略
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
