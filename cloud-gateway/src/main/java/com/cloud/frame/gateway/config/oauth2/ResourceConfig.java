package com.cloud.frame.gateway.config.oauth2;

import com.cloud.frame.common.bean.FilterIgnorePropertiesConfig;
import com.cloud.frame.gateway.config.oauth2.handler.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-10 17:49
 **/

@EnableResourceServer
@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {

        resources
                .tokenServices(tokenServices())
                .stateless(true);
       // resources.accessDeniedHandler(accessDeniedHandler);
       // resources.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {

        // 配置RemoteTokenServices，用于向AuththorizationServer验证token
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setAccessTokenConverter(accessTokenConverter());

        // 为restTemplate配置异常处理器，忽略400错误，
        RestTemplate restTemplate = restTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            // Ignore 400
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        tokenServices.setRestTemplate(restTemplate);

        tokenServices.setCheckTokenEndpointUrl("http://oauth2-server/oauth/check_token");

        tokenServices.setClientId("client");
        tokenServices.setClientSecret("secret");
        return tokenServices;

    }


    // 配置JWT转换器
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置资源服务器的拦截规则
/*        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/role/**").access("hasRole('USER')")
                .antMatchers("/user/**").authenticated() // /user/** 端点的访问必须要验证后
               // .antMatchers("/cloud-web/web/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/



        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                exp =  http.authorizeRequests();

        exp.and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/auth/sumit");
        filterIgnorePropertiesConfig.getUrls().forEach(url -> exp.antMatchers(url).permitAll());



        exp.anyRequest().authenticated();
    }
}
