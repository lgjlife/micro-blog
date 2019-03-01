package com.cloud.microblog.user.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IUserInfoService // implements UserInfoService
{
/*
    @Autowired
    UserMapper userMapper;

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
    public ReturnCode saveSetting(Map<String, Object> map) {

        ReturnCode returnCode = null;

        String nickName = (String) map.get("nickName");
        String phone = (String) map.get("phoneNum");
        String email = (String) map.get("email");

        log.debug("info = {}"+ map.values());

        *//*if(!UserRegexUtil.isMobile(phone)){
            return UserReturnCode.FORMAT_PHONE_NUM_ERR;
        }
        else if(!UserRegexUtil.isEmail(email)){
            return UserReturnCode.FORMAT_EMAIL_ERR;
        }*//*
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        if(user != null){
            map.put("userId",user.getUserId());
            userMapper.updateInfoByPrimaryKey(map);
            User user1 = userMapper.selectByPrimaryKey(user.getUserId());
            SessionUtils.set(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY,user1);

            return UserReturnCode.INFO_RESET_SUCCESS;
        }
        else {

            return UserReturnCode.ACCOUT_UNLOGIN;
        }


    }

    *//**
     *功能描述
     * @author lgj
     * @Description  上传头像处理
     * @date 2/21/19
     * @param:
     * @return:
     *
    *//*

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
            User user = (User)SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
            if(user != null){
                HashMap<String,Object> map = new HashMap<String,Object> ();
                map.put("userId",user.getUserId());
                map.put("headerUrl",subPath);
                userMapper.updateInfoByPrimaryKey(map);

                user.setHeaderUrl(subPath);
                SessionUtils.set(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY,user);
                File newfile = new File(savePath);
                log.debug("newfile={}", newfile.getAbsolutePath());
                file.transferTo(newfile);
                log.debug("文件上传成功！");
                return subPath;


            }
            throw  new NullPointerException();
        }
        throw  new NullPointerException();
    }

    private String  getRandomFileName(){
        String random = String.valueOf(new Random().nextInt(10000));
        String timestamp = String.valueOf(new Date().getTime());

        return  random+"-"+timestamp;

    }*/
}
