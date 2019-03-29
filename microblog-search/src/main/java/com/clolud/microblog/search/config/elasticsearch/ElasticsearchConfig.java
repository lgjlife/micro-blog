package com.clolud.microblog.search.config.elasticsearch;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;

@Slf4j
//@Configuration
public class ElasticsearchConfig {

    @Bean
    public TransportClient client() throws Exception{

        PreBuiltTransportClient client = null;

        log.info("ElasticsearchConfig  配置");
        TransportAddress  node = new TransportAddress(InetAddress.getByName("localhost"),9300);
        //es集群配置（自定义配置） 连接自己安装的集群名称
        Settings settings = Settings.builder()
                .put("cluster.name","elasticsearch")
                .build();
        log.info("创建节点");
        try{
            client = new PreBuiltTransportClient(settings);
            client.addTransportAddress(node);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }



        return client;

    }
}
