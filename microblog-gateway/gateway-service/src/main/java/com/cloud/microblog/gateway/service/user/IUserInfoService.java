package com.cloud.microblog.gateway.service.user;

import com.cloud.microblog.common.utils.SessionUtils;
import com.cloud.microblog.gateway.dao.model.User;
import com.cloud.microblog.gateway.service.utils.UserSessionKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IUserInfoService  implements  UserInfoService{

    @Override
    public User userInfo() {
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);

        if(user != null){
            log.debug("user info = " + user);
        }
        else {
            log.debug("user is null  ");
        }
        return  user;
    }

    @Override
    public String upLoadHeaderImg() throws Exception {



        return null;
    }
}
