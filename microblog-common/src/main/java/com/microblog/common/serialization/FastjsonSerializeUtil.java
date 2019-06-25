package com.microblog.common.serialization;

import com.alibaba.fastjson.JSON;


/**
 *功能描述
 * @author lgj
 * @Description fastjson ,对象属性需要实现get/set
 * @date 3/24/19
*/
public class FastjsonSerializeUtil  {

    public static  <T> byte[] serialize(T obj) {
        if (obj  == null){
            throw new NullPointerException();
        }

        String json = JSON.toJSONString(obj);
        byte[] data = json.getBytes();
        return data;
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {

        T obj = JSON.parseObject(new String(data),clazz);
        return obj;
    }


}
