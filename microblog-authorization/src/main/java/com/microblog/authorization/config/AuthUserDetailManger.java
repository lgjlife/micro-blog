package com.microblog.authorization.config;

import com.microblog.authorization.Filter.UserContext;
import com.microblog.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;


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
    private UserService userService;


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

        String userType = UserContext.getUserType();

        log.info("userType = " + userType);


        if(UserContext.MANAGER_TYPE.equals(userType)){
            UserDetails userDetails = userService.getManangerUser(userName);
           return userDetails;
        }
        else if(UserContext.NORMAL_TYPE.equals(userType)){
            return userService.getNormalUser(userName);
        }
        else {

            throw new BadCredentialsException("client-id错误!");
        }




    }
}
