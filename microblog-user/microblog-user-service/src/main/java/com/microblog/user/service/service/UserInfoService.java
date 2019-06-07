package com.microblog.user.service.service;

import com.microblog.common.code.ReturnCode;
import com.microblog.user.dao.model.User;
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
    User userInfo(Long userId);
    User userInfoById(Long userId);
    String upLoadHeaderImg(Long userId,MultipartHttpServletRequest multiRequest) throws  Exception;
    ReturnCode saveSetting(Long userId,Map<String, Object> map);
}
