package com.microblog.user.service.service.impl;

import com.microblog.common.code.ReturnCode;
import com.microblog.common.code.UserReturnCode;
import com.microblog.common.dto.UserInfoDto;
import com.microblog.common.utils.UserRegexUtil;
import com.microblog.filesystem.provider.FSProvider;
import com.microblog.filesystem.upload.UpLoadObject;
import com.microblog.filesystem.upload.UpLoadObjectBuilder;
import com.microblog.user.dao.mapper.UserMapper;
import com.microblog.user.dao.model.User;
import com.microblog.user.service.config.utils.RedisStringUtil;
import com.microblog.user.service.constants.UserRedisKeyUtil;
import com.microblog.user.service.feign.PointsFeignService;
import com.microblog.user.service.service.UserInfoService;
import com.microblog.user.service.utils.fastdfs.FastdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class IUserInfoService  implements UserInfoService
{

    @Autowired
FastdfsUtil fastdfsUtil;

    @Autowired
    FSProvider fastdfsClient;


    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisStringUtil redisStringUtil;


    @Autowired
    UserMapper userMapper;

    @Autowired
    PointsFeignService pointsFeignService;


    @Override
    public UserInfoDto userInfo(Long userId) {
        log.debug("userId = " + userId);

        UserInfoDto userInfoDto  =  (UserInfoDto)redisStringUtil.get(UserRedisKeyUtil.LOGIN_USER_INFO_KEY.getPrefix()+userId);
        if(userInfoDto == null){
            User user = userMapper.selectUserInfo(userId);
            Long points = pointsFeignService.queryPoints(userId);
            userInfoDto = new UserInfoDto();
            BeanUtils.copyProperties(user,userInfoDto);
            userInfoDto.setPoints(points);
            redisStringUtil.set(UserRedisKeyUtil.LOGIN_USER_INFO_KEY.getPrefix()+userId,
                    userInfoDto,
                    UserRedisKeyUtil.LOGIN_USER_INFO_KEY.getTimeout());
        }

        return  userInfoDto;
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
    public ReturnCode saveSetting(Long userId,Map<String, Object> map) {



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
        User user = getUser(userId);
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
    public String upLoadHeaderImg(Long userId,MultipartHttpServletRequest multiRequest) throws Exception {


        List<MultipartFile> fileList = new ArrayList<>();
        //取得request中的所有文件名
        fileList = multiRequest.getFiles("file");
        if (fileList == null || fileList.size() <= 0) {
            log.debug("file is null");
            throw  new NullPointerException();
        }
        MultipartFile file = fileList.get(0);


        log.debug("{}--{}", file.getName(), file.getOriginalFilename());
        //upload to fastdfs
        UpLoadObject upLoadObject = new UpLoadObjectBuilder().name( file.getOriginalFilename())
                .size(file.getSize()).inputStream(file.getInputStream())
                .metaDate(new HashMap<>()).build();
        String dfsImgPath =  fastdfsClient.upLoad(upLoadObject);

    /*    String dfsImgPath = fastdfsClient.upLoad(FastdfsGroup.USER_HEADER_IMAGE_GROUP,
                file.getOriginalFilename(),file.getInputStream(),file.getSize(),
                new HashSet<MetaData>())*/

        //数据保存
        User user = getUser(userId);
        String oldHeaderUrl = user.getHeaderUrl();

        if(user != null){
            HashMap<String,Object> map = new HashMap<String,Object> ();
            map.put("userId",user.getUserId());
            map.put("headerUrl",dfsImgPath);
            userMapper.updateInfoByPrimaryKey(map);
            user.setHeaderUrl(dfsImgPath);
            log.debug("文件上传成功！");
            redisStringUtil.delete(UserRedisKeyUtil.LOGIN_USER_INFO_KEY.getPrefix()+user.getUserId());

            return dfsImgPath;
        }
        log.debug("文件上传失败！");
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
            user = userMapper.selectUserInfo(userId);
            redisStringUtil.set(UserRedisKeyUtil.LOGIN_USER_KEY.getPrefix()+userId,
                    user,
                    UserRedisKeyUtil.LOGIN_USER_KEY.getTimeout());
        }

        return  user;
    }



}
