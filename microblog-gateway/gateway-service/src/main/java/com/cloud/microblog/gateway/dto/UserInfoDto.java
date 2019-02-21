package com.cloud.microblog.gateway.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class UserInfoDto {

    public static void main(String args[]){
        Source source = new Source("libai",34);
        Source1 source1 = new Source1("nanning",true);
        Target target = new Target();
        BeanUtils.copyProperties(source,target);
        BeanUtils.copyProperties(source1,target);


        log.debug("target = {}",target);
    }
}

@Data
class Source{

    private String  name;
    private int     age;

    public Source(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

@Data
class Source1{

    private String  home;
    private boolean  flag;

    public Source1(String home, boolean flag) {
        this.home = home;
        this.flag = flag;
    }
}

@Data
class Target{
    private String  name;
    private int     age;
    private String  home;
    private boolean  flag;

}
