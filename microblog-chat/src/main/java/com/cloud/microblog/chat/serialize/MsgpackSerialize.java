package com.cloud.microblog.chat.serialize;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;

/**
 * 功能描述
 *
 * @author lgj
 * @Description JDK序列化方式
 * @date 1/24/19
 */
public class MsgpackSerialize implements Serialize {

    private static MessagePack pack = new MessagePack();

    @Override
    public Object getObject(byte[] b) {
        Object obj;
        try {
            obj = pack.read(b);
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public byte[] writeObject(Object obj) {

        System.out.println("obj = " + obj);
        byte[] b = null;
        try {
            b = pack.write(obj);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return b;
    }


    public static void main(String args[]) {
        User user1 = new User("lgj", 12);
        MessagePack pack = new MessagePack();
        try {
            byte[] b = pack.write(user1);
            System.out.println(b.length);

            User user2 = (User) pack.read(b, User.class);

            System.out.println(user2);

            Value v = pack.read(b);
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        /*MsgpackSerialize serialize = new MsgpackSerialize();
        byte[] bObj = serialize.writeObject(user1);

        System.out.println("length = " + bObj.length);*/

        //  User user2 = (User)serialize.getObject(bObj);

        //  System.out.println(user2);

    }
}


