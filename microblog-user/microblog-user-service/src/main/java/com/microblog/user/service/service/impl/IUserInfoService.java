package com.microblog.user.service.service.impl;

import com.microblog.common.code.ReturnCode;
import com.microblog.common.code.UserReturnCode;
import com.microblog.common.utils.UserRegexUtil;
import com.microblog.user.dao.mapper.UserMapper;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.config.utils.RedisStringUtil;
import com.microblog.user.service.constants.UserRedisKeyUtil;
import com.microblog.user.service.service.UserInfoService;
import com.microblog.user.service.utils.fastdfs.FastdfsGroup;
import com.microblog.user.service.utils.fastdfs.FastdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Slf4j
@Service
public class IUserInfoService  implements UserInfoService
{

    @Autowired
    FastdfsUtil fastdfsUtil;

    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisStringUtil redisStringUtil;


    @Autowired
    UserMapper userMapper;

    @Override
    public User userInfo() {
        return  getUser(getUserId());
    }

    @Override
    public User userInfoById(Long id) {
        return  getUser(id);
    }

    /**
     *功能描述
     * @author lgj
     * @Description  保存用户信息 设置
     * @date 3/2/19
     * @param:
     * @return:
     *
    */
    @Override
    public ReturnCode saveSetting(Map<String, Object> map) {

        ReturnCode returnCode = null;

        String nickName = (String) map.get("nickName");
        String phone = (String) map.get("phoneNum");
        String email = (String) map.get("email");

        log.debug("info = {}"+ map.values());

        if(!UserRegexUtil.isMobile(phone)){
            return UserReturnCode.FORMAT_PHONE_NUM_ERR;
        }
        else if(!UserRegexUtil.isEmail(email)){
            return UserReturnCode.FORMAT_EMAIL_ERR;
        }
        User user = getUser(getUserId());
        user.setNickName(nickName);
        user.setPhoneNum(phone);
        user.setEmail(email);
        if(user != null){
            map.put("userId",user.getUserId());
            userMapper.updateInfoByPrimaryKey(map);
            User user1 = userMapper.selectByPrimaryKey(user.getUserId());

            redisStringUtil.set(UserRedisKeyUtil.LOGIN_USER_KEY.getPrefix()+user1.getUserId(),
                    user1,
                    UserRedisKeyUtil.LOGIN_USER_KEY.getTimeout());


            return UserReturnCode.INFO_RESET_SUCCESS;
        }
        else {

            return UserReturnCode.ACCOUT_UNLOGIN;
        }


    }

    /**
     *功能描述
     * @author lgj
     * @Description  上传头像处理
     * @date 2/21/19
     * @param:
     * @return:
     *
    */

    @Override
    @Transactional
    public String upLoadHeaderImg(MultipartHttpServletRequest multiRequest) throws Exception {


        List<MultipartFile> fileList = new ArrayList<>();
        //取得request中的所有文件名
        fileList = multiRequest.getFiles("file");
        if (fileList == null || fileList.size() <= 0) {

            log.debug("file is null");
            throw  new NullPointerException();
        }


        for(MultipartFile file:fileList) {
            log.debug("{}--{}", file.getName(), file.getOriginalFilename());

            String staticPath = "/nginx/microblog/static";
            String imgPath = "/img/header";
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.debug("OriginalFilename = {},suffix={}",originalFilename,suffix );


            //创建文件夹
            File saveFile = new File(staticPath+imgPath);
            if (!saveFile.exists()) {
                log.debug("path:{} not exists,create dir");
                saveFile.mkdir();
                log.debug("saveFile.exists? {}", saveFile.exists());
            }

            String subPath = imgPath + "/" + getRandomFileName() + suffix;
            String savePath = staticPath + subPath;

            //数据保存
            User user = getUser(getUserId());
            if(user != null){
                HashMap<String,Object> map = new HashMap<String,Object> ();
                map.put("userId",user.getUserId());
                map.put("headerUrl",subPath);
                userMapper.updateInfoByPrimaryKey(map);

                user.setHeaderUrl(subPath);
                redisStringUtil.set(UserRedisKeyUtil.LOGIN_USER_KEY.getPrefix()+user.getUserId(),
                        user,
                        UserRedisKeyUtil.LOGIN_USER_KEY.getTimeout());
                File newfile = new File(savePath);
                log.debug("newfile={}", newfile.getAbsolutePath());



                file.transferTo(newfile);
                fastdfsUtil.upload(FastdfsGroup.USER_HEADER_IMAGE_GROUP,
                        file.getName(),file.getInputStream(),file.getSize(),
                        null);

                log.debug("文件上传成功！");
                return subPath;


            }
            throw  new NullPointerException();
        }
        throw  new NullPointerException();
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取随机字符串作为图片命名   随机数（0-10000）- 当前时间的时间戳
     * @date 3/1/19
     * @param:
     * @return:
     *
    */
    private String  getRandomFileName(){
        String random = String.valueOf(new Random().nextInt(10000));
        String timestamp = String.valueOf(new Date().getTime());

        return  random+"-"+timestamp;

    }


    private  User  getUser(long userId){


        User user =  (User)redisStringUtil.get(UserRedisKeyUtil.LOGIN_USER_KEY.getPrefix()+userId);
        if(user == null){
            user = userMapper.selectByPrimaryKey(userId);
            redisStringUtil.set(UserRedisKeyUtil.LOGIN_USER_KEY.getPrefix()+userId,
                    user,
                    UserRedisKeyUtil.LOGIN_USER_KEY.getTimeout());
        }

        return  user;
    }


    private  Long  getUserId(){
        Long userId =  (Long)request.getAttribute("userId");
        log.debug("userId = " + userId);
        return userId;
    }
}
