package com.microblog.chat.service;

import com.microblog.chat.dto.ChatUserDto;

import java.util.List;

public interface UserService {

    List<ChatUserDto> list();
}
