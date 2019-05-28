package com.microblog.common.auth;


import com.microblog.common.zk.ZkCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAutoConfiguration {

    @Autowired(required = false)
    private AuthInterceptorAdapter authInterceptorAdapter;

    @Autowired
    private ZkCli zkCli;



    @Bean
    @ConditionalOnBean(value={AuthInterceptorAdapter.class,ZkCli.class})
    public AuthService authService(){

        AuthService authService = new AuthService(zkCli);
        authService.setAuthInterceptorAdapter(authInterceptorAdapter);
        authService.registerAuthConfig();

        return authService;

    }

}
