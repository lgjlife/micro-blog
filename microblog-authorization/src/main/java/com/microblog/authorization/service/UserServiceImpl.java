package com.microblog.authorization.service;

import com.microblog.authorization.dao.model.ManagerUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *功能描述 用户服务，用于获取　UserDetails
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ManagerUserService managerUserService;


    @Override
    public UserDetails getManangerUser(String username) {

        ManagerUser managerUser = managerUserService.queryManagerUser(username);

        if(managerUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        log.debug("managerUser = " + managerUser);
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = new User(managerUser.getUsername(),
                passwordEncoder.encode(managerUser.getPassword()), authorities);
        return userDetails;
    }

    @Override
    public UserDetails getNormalUser(String username) {
        return null;
    }
}
