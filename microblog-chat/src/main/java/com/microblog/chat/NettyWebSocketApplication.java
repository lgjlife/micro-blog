package com.microblog.chat;


import com.microblog.chat.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//@EnableScheduling

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.microblog.chat.*", "com.microblog.common"})
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
