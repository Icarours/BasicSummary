package com.syl.basicsummary.eventbus;

/**
 * Created by j3767 on 2017/3/4.
 *
 * @Describe
 * @Called
 */

public class MessageEvent {
    String name;
    int age;

    public MessageEvent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
