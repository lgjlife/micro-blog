package com.cloud.microblog.chat.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述
 *
 * @author lgj
 * @Description JDK序列化方式
 * @date 1/24/19
 */

@Slf4j
public class FastjsonSerialize implements Serialize {


    @Override
    public Object getObject(byte[] b) {
        log.info("length = " + b.length);
        Object obj = (Object) JSON.parseObject(b, User.class);
        return obj;
    }

    @Override
    public byte[] writeObject(Object obj) {
        String str = JSON.toJSONString(obj);
        log.info("str = " + str);
        return str.getBytes();
    }


    public static void main(String args[]) {

        User user = new User("dmego", 1);
        System.out.println(user);
        String UserJson = JSON.toJSONString(user, false);
        System.out.println("简单java类转json字符串:" + UserJson);

        String txt = "{\"name\":\"dmego\",\"age\":1}";
        User user1 = JSON.parseObject(txt, new TypeReference<User>() {
        });
        System.out.println(user1);
        //  System.out.println("len = " + user1.size());
        // System.out.println(user1.get(0));


        User user2 = new User("lgj", 12);

        FastjsonSerialize serialize = new FastjsonSerialize();

        byte[] bObj = serialize.writeObject(user2);

        User user3 = (User) serialize.getObject(bObj);

        System.out.println(user3);

    }
}


