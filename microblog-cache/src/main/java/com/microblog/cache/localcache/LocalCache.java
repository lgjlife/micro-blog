package com.microblog.cache.localcache;

public interface LocalCache<Sring,T> {

    void init(T defaultObject);
    void set(String key,T object);
    T get(String key) ;
    void remove(String key);
    void removeaAll();
}
