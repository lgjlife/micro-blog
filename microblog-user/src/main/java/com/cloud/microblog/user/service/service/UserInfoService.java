package com.cloud.microblog.user.service.service;

import com.cloud.microblog.common.code.ReturnCode;
import com.cloud.microblog.user.dao.model.User;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

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

    ReturnCode saveSetting(Map<String, Object> map);
}
