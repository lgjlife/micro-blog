package com.cloud.microblog.gateway.service.utils;

public enum UserSessionKeyUtil {

    REGISTER_AES_KEYPAIR_KEY(30,"注册帐号时AES KEYPAIR"),
    PHONE_EMAIL_VERIFICATION_CODE_KEY(2,"电话邮件验证码"),
    IMG_VERIFICATION_CODE_KEY(2,"邮件验证码"),
    CURRENT_LOGIN_USER_KEY(120,"当前登录的用户");

    //超时时间，单位 分钟
    private  int timeout;
    private  String desc;

    UserSessionKeyUtil(int timeout, String desc) {
        this.timeout = timeout;
        this.desc = desc;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getDesc() {
        return desc;
    }
}
