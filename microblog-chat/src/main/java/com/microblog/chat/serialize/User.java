package com.microblog.chat.serialize;

import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Message;

@Setter
@Getter
@Message
public class User {

    public String name;
    public Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}