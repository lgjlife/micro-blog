package com.microblog.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class MyWebSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    CsrfTokenRepository csrfTokenRepository;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
        .and()
                .authorizeRequests()
                .anyRequest().permitAll();


    }


}
