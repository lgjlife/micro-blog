package com.cloud.microblog.chat.dto;


import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ChatUserDto {

    private String nickName;
    private String remarksName;
    private String HeaderUrl;
    private String status;
    private String  addr;    // 地址
    private Channel channel;// 通道

}
