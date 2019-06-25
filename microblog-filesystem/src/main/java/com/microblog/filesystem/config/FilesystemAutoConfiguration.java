package com.microblog.filesystem.config;

import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.microblog.filesystem.provider.FSProvider;
import com.microblog.filesystem.fastdfs.FastdfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *功能描述
 * @author lgj
 * @Description  文件系统配置类
 *
fdfs:
    so-timeout: 1501
    connect-timeout: 100
    thumb-image: # 缩略图
    width: 60
    height: 60
    tracker-list: # tracker地址
        - 172.17.0.1:22122

 * @date 6/18/19
*/

@Configuration
public class FilesystemAutoConfiguration {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DefaultFastFileStorageClient storageClient;


    @Bean
    public FSProvider fastdfsClient(){
        FastdfsClient fastdfsClient = new FastdfsClient(storageClient);
        return fastdfsClient;
    }
}
