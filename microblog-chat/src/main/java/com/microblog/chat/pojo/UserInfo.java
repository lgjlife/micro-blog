package com.microblog.chat.pojo;

import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserInfo {
    private String  userId;  // UID
    private String  addr;    // 地址
    private Channel channel;// 通道

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", addr='" + addr + '\'' +
                ", channel=" + channel +
                '}';
    }
}

