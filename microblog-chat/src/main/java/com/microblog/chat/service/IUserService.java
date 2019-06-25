package com.microblog.chat.service;


import com.microblog.chat.dto.ChatUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class IUserService implements  UserService {


    @Override
    public List<ChatUserDto> list() {

        List<ChatUserDto> chatUserDtos = new LinkedList<>();

        for(int i = 0 ; i< 5 ;i++){
            ChatUserDto userDto =   new ChatUserDto();

            userDto.setNickName("NickName-"+i);
            userDto.setRemarksName("RemarksName-"+i);
            userDto.setHeaderUrl("/img/header/"  +  new Random().nextInt(7)+ ".jpg");

            chatUserDtos.add(userDto);
        }
        return chatUserDtos;
    }
}
