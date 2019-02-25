package com.cloud.microblog.chat.serialize;

public interface Serialize {

    Object getObject(byte[] b);

    byte[] writeObject(Object obj);
}
