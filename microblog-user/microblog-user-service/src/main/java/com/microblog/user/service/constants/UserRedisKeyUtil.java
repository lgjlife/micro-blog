package com.microblog.user.service.constants;

public enum  UserRedisKeyUtil {

    VERIF_CODE_KEY("VERIF_CODE_KEY:",30,"注册验证码前缀"),
    IMG_VERIF_CODE_KEY("IMG_VERIF_CODE_KEY:",30,"注册图片验证码前缀"),
    KEYPAIR_PRIVATE_MODULUS_KEY("KEYPAIR_PRIVATE_MODULUS_KEY:",30,"密钥MODULUS"),
    KEYPAIR_PRIVATE_EXPONENT_KEY("KEYPAIR_PRIVATE_EXPONENT_KEY:",30,"密钥EXPONENT"),
    LOGIN_USER_KEY("LOGIN_USER_KEY:",30,"当前登录的用户"),
    LOGIN_USER_INFO_KEY("LOGIN_USER_INFO_KEY:",30,"当前登录的用户信息"),;


    String prefix;
    String desc;
    //时间为分钟
    long  timeout;


    UserRedisKeyUtil(String prefix, long  timeout, String desc) {
        this.prefix = prefix;
        this.desc = desc;
        this.timeout = timeout;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDesc() {
        return desc;
    }

    public long getTimeout() {
        return timeout;
    }
}
