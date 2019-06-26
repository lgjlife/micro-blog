package com.microblog.common1.zk;


import org.apache.curator.framework.recipes.cache.ChildData;

public interface ListenerEventHandler {

    void addHandler(ChildData data);

    void removeHandler(ChildData data);

    void updateHandler(ChildData data);


}
