package com.cloud.microblog.gateway.controller.session;


import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.common.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/set")
    public void setsession(@RequestParam String data){
        log.debug("data = " + data);

       // SessionUtils.set(data,data);

       // String  read = (String) SessionUtils.get(data);
        Demo demo = new Demo("name",112);
        redisTemplate.opsForValue().set(data,demo);
        Demo  read = (Demo)redisTemplate.opsForValue().get(data);
        log.debug("read data = " + read);
    }

    @PrintUrlAnno
    @GetMapping("/set/1")
    public void setsession(){


         SessionUtils.set("aaa","bbb");

         String  read = (String) SessionUtils.get("aaa");

        log.debug("read data = " + read);
    }

    @PrintUrlAnno
    @GetMapping("/set/2")
    public void set2(){

        redisTemplate.opsForValue().set("aaa","bbb");
        String  read = (String)redisTemplate.opsForValue().get("aaa");
        log.debug("read data = " + read);
    }


}


class Demo{

    private String  name;

    private int age;

    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Demo() {
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
