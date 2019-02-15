package com.cloud.frame.cloudzuul.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-18 21:25
 **/

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    //省略get和set方法、构造函数

}
