package com.microblog.test.jmx.config;

public class ServerInfo implements ServerInfoMBean {

    @Override
    public int getInfo(){

        return DataUtil.count;
    }

    @Override
    public void setInfo(int count) {
        DataUtil.count = count;
    }
}
