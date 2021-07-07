package com.functioninterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StreamTest {
    public static void main(String[] args) {
        /**
         * 现有5 个用户，筛选：
         * 1.ID必须是偶数
         * 2.年龄必须大于18
         * 3.用户名转化为大写
         * 4.用户名倒序
         * 5.只输出一个用户
         */

        User user1 = new User(1,"a",23);
        User user2 = new User(2,"b",12);
        User user3 = new User(3,"c",22);
        User user4 = new User(4,"d",28);
        User user5 = new User(5,"e",17);
        /* 集合就是存储 */
        List<User> list = Arrays.asList(user1,user2,user3,user4,user5);

        /* 计算需要交给流去处理 */

        list.stream()
                .filter(user->{return user.getId()%2==0;})
                .filter((user)->{return user.getAge()>18;})
                .map((user)->{return user.getName().toUpperCase(Locale.ROOT);})
                .sorted((uu1,uu2)->{return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);

    }
}

/**
 * user类
 */
class User{
    int id;
    String name;
    int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
