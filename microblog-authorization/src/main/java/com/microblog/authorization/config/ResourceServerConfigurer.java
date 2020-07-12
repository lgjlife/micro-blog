package com.microblog.authorization.config;

/**
 * Created by Steven on 2020/6/12.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *功能描述 访问权限控制
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@EnableResourceServer
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                //资源服务器获取publickey,这个publickey用于对jwt进行解密
                .antMatchers("/jwt/publickey").permitAll()
                //登录过程获取publickey，这个用于登录时用于密码加密
                .antMatchers("/login/publickey").permitAll()
                //.antMatchers("/api/auth/login/publickey").permitAll()
                //其他uri都要进行登录验证
                .anyRequest().authenticated();
    }

}
