package com.cloud.microblog.chat;


import com.cloud.microblog.chat.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.microblog.chat.*","com.cloud.microblog.common"})
public class NettyWebSocketApplication {

    @Autowired
    public static WebSocketServer webSocketServer;

    public static void main(String args[]) {
        SpringApplication.run(NettyWebSocketApplication.class, args);

        try {
            new WebSocketServer().run(8110);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
