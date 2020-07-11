package com.microblog.authorization.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
@Slf4j
public class AuthUserDetailManger implements UserDetailsManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails userDetails) {

        log.info("createUser");
        String userName = userDetails.getUsername();

        log.info("userName = " + userName);


    }

    @Override
    public void updateUser(UserDetails userDetails) {
        log.info("updateUser");
        String userName = userDetails.getUsername();

        log.info("userName = " + userName);
    }

    @Override
    public void deleteUser(String userName) {
        log.info("deleteUser");

        log.info("userName = " + userName);
    }

    @Override
    public void changePassword(String s, String s1) {
        log.info("changePassword");

    }




    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        if(!"my-username".equals(userName)){
//            throw new UnknownAccountException();
//        }
        //在通过用户名密码获取token时会调用
        //1.从数据库获取

        log.info("loadUserByUsername +  " + userName);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER1"));
        authorities.add(new SimpleGrantedAuthority("ADMIN1"));

        log.info("authorities = " + authorities);

        UserDetails userDetails1 = new User("my-username",
                passwordEncoder.encode("my-password1"), authorities);

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_READ");
                return role;
            }

            @Override
            public String getPassword() {
                return passwordEncoder.encode("my-password1");
            }

            @Override
            public String getUsername() {
                return "my-username";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };


        log.info("userDetails = " + userDetails);
        return userDetails1;
    }
}
