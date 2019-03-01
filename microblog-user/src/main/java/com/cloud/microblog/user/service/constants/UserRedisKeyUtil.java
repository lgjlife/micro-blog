package com.cloud.microblog.user.service.constants;

public enum  UserRedisKeyUtil {

    VERIF_CODE_KEY("VERIF_CODE_KEY:",30,"注册验证码前缀"),
    IMG_VERIF_CODE_KEY("IMG_VERIF_CODE_KEY:",30,"注册图片验证码前缀"),
    KEYPAIR_KEY("KEYPAIR_KEY:",30,"密钥"),
    KEYPAIR_PRIVATE_KEY("KEYPAIR_PRIVATE_KEY:",30,"密钥"),
    KEYPAIR_PRIVATE_MODULUS_KEY("KEYPAIR_PRIVATE_MODULUS_KEY:",30,"密钥"),
    KEYPAIR_PRIVATE_EXPONENT_KEY("KEYPAIR_PRIVATE_EXPONENT_KEY:",30,"密钥"),
    TEST_KEY("TEST_KEY:",30,"密钥"),;

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
