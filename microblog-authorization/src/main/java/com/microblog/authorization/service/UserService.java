package com.microblog.authorization.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails getManangerUser(String username);

    UserDetails getNormalUser(String username);
}
