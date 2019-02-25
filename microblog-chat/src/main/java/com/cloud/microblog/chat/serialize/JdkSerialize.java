package com.cloud.microblog.chat.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 功能描述
 *
 * @author lgj
 * @Description JDK序列化方式
 * @date 1/24/19
 */
public class JdkSerialize implements Serialize {


    @Override
    public Object getObject(byte[] b) {
        ByteArrayInputStream bis = new ByteArrayInputStream(b);

        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public byte[] writeObject(Object obj) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new byte[0];
    }


    public static void main(String args[]) {
        User user1 = new User("lgj", 12);

        JdkSerialize jdkSerialize = new JdkSerialize();
        byte[] bObj = jdkSerialize.writeObject(user1);

        User user2 = (User) jdkSerialize.getObject(bObj);

        System.out.println(user2);

    }
}