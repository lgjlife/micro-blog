package com.cloud.microblog.chat.service;

import com.cloud.microblog.chat.dto.ChatUserDto;

import java.util.List;

public interface UserService {

    List<ChatUserDto> list();
}
