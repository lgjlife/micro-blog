package com.microblog.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordEncode {



    public static void main(String args[]){

        String originPassword = "123456789";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        String  newPassword  = encoder.encode(originPassword);

        log.info("原始密码={}({}),加密之后的密码={}({})",
                originPassword,originPassword.length(),
                newPassword,newPassword.length()
        );
    }
}
