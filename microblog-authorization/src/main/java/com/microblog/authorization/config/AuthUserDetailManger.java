package com.microblog.authorization.config;

import com.microblog.authorization.util.Md5Util;
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


/**
 *功能描述  用户管理，通过username来获取用户信息等操作
 * @author lgj
 * @Description 　　　
 * @date 　
*/
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

    /**
     * 每次客户端获取access_token的时候都会调用该方法加载用户信息
     * 1.通过用户名称获取信息，账户状态，权限信息，密码
     * 2.密码需要使用PasswordEncoder加密
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.info("loadUserByUsername +  " + userName);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER1"));
        authorities.add(new SimpleGrantedAuthority("ADMIN1"));

        log.info("authorities = " + authorities);

        UserDetails userDetails1 = new User("my-username",
                passwordEncoder.encode(Md5Util.md5("my-password1")), authorities);

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_READ");
                return role;
            }

            @Override
            public String getPassword() {
                return passwordEncoder.encode(Md5Util.md5("my-password1"));
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
