package com.microblog.chat.pojo;

import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Message;

import java.io.Serializable;

@Getter
@Setter
@Message
public class TestUser implements Serializable {

    String name;
    Integer age;
    Integer height;

    public TestUser() {
    }

    public TestUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public TestUser(String name, Integer age, Integer height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
