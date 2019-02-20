package com.cloud.microblog.gateway.service.user;

import com.cloud.microblog.gateway.dao.model.User;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UserInfoService {

    /**
     *功能描述
     * @author lgj
     * @Description  获取当前登录的用户
     * @date 2/19/19
     * @param:
     * @return:
     *
     */
    User userInfo();

    String upLoadHeaderImg(MultipartHttpServletRequest multiRequest) throws  Exception;
}
