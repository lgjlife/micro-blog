package com.microblog.chat.serialize;

public interface Serialize {

    Object getObject(byte[] b);

    byte[] writeObject(Object obj);
}
