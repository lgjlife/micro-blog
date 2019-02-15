package com.cloud.frame.gateway.config.oauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-10 17:32
 **/

//@Configuration
//@EnableOAuth2Sso
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //解决Refused to display 'http://localhost:7004/zuul/eureka-server-api'
        // in a frame because it set 'X-Frame-Options' to 'deny'
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/user1/**").authenticated()
                .antMatchers("/eureka-server-api/**").permitAll()
                .antMatchers("/eureka/**").permitAll()
                .antMatchers("/cloud-web/web/**").access("hasRole('USER')")//.authenticated()
                .and()
                .formLogin().loginPage("/llll").successForwardUrl("/index")
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .logoutSuccessUrl("/");



    }

}
