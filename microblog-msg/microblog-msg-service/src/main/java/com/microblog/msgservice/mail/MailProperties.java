package com.microblog.msgservice.mail;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *功能描述 
 * @author lgj
 * @Description 邮件配置类
 * @date 
*/
@Data
@ConfigurationProperties(prefix = "microblog.mail")
@Configuration
public class MailProperties {

    /*邮件编码*/
    private String encoding = "UTF-8";
    /*邮件服务器*/
    private String host = "smtp.sina.com";
    /*发送的源邮件地址*/
    private String sourceMail;
    /*发送的源邮件的密码*/
    private String password;
    /*协议*/
    private String protocol = "smtp";

}
