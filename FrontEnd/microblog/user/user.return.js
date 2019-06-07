var userRet={


    "QUERY_USER_INFO_SUCCESS":{
        "code": "1082",
        "message": "查询用户信息成功",
    },
    "QUERY_USER_INFO_FAIL":{
        "code": "1083",
        "message": "查询用户信息失败",
    },

    //空参数 0
    "ERROR_PARAM":{
        "code": "0",
        "message": "输入参数无效",
    },
    //验证码 100 - 109
    "VALIDATE_CODE_SEND_SUCCESS":{
        "code": "100",
        "message": "验证码发送成功",
    },
    "VALIDATE_CODE_SEND_FAIL":{
        "code": "101",
        "message": "验证码发送失败",
    },
    "VALIDATE_CODE_CHECK_PASS":{
        "code": "102",
        "message": "验证码校验通过",
    },
    "VALIDATE_CODE_CHECK_FAIL":{
        "code": "103",
        "message": "验证码校验失败",
    },
    "IMG_VALIDATE_CODE_CHECK_PASS":{
        "code": "104",
        "message": "图片验证码校验通过",
    },
    "IMG_VALIDATE_CODE_CHECK_FAIL":{
        "code": "105",
        "message": "图片验证码校验失败",
    },
    "REQUEST_RSA_KEY_SUCCESS":{
        "code": "106",
        "message": "获取RSA密钥成功",
    },
    "REQUEST_RSA_KEY_FAIL":{
        "code": "107",
        "message": "获取RSA密钥失败",
    },
    //登录相关　1000-1029
    "LOGIN_SUCCESS":{
        "code": "1000",
        "message": "用户登录成功",
    },
    "LOGIN_FAIL":{
        "code": "1001",
        "message": "用户登录失败",
    },
    "LOGIN_PASSWORD_ERR":{
        "code": "1002",
        "message": "用户登录失败,密码错误",
    },
    "LOGIN_LOCK_ACCOUNT":{
        "code": "1003",
        "message": "用户登录失败，账户被锁定",
    },
    "LOGIN_PASSWORD_ERR_MORE":{
        "code": "1004",
        "message": "用户登录失败，密码错误过多",
    },
    "LOGIN_GET_KEYPAIR_FAIL":{
        "code": "1005",
        "message": "登陆时获取keypair失败",
    },
    "LOGIN_GET_KEYPAIR_SUCCESS":{
        "code": "1006",
        "message": "登陆时获取keypair成功",
    },
    "LOGIN_UNKNOW_ACCOUT":{
        "code": "1007",
        "message": "帐号不存在",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    "":{
        "code": "",
        "message": "",
    },
    ACCOUT_UNLOGIN(1008,"帐号未登录"),

    //退出登录
    LOGOUT_SUCCESS(1015,"用户退出登录成功"),
    LOGOUT_FAIL(1016,"用户退出登录失败"),

    //注册相关　1030-1059
    ACCOUNT_EXIST(1030,"帐号注册失败,帐号已经存在"),
    NAME_EXIST(1031,"该用户名已经存在"),
    PHONE_NUM_EXIST(1032,"该手机号已经注册"),
    EMAIL_EXIST(1033,"该邮箱已经注册"),
    CAN_REGISTER(1034,"帐号未注册，可以进行注册"),

    REGISTER_SUCCESS(1035,"帐号注册成功"),
    REGISTER_FAIL(1036,"帐号注册失败"),
    REGISTER_GET_VALIDATE_CODE_SUCCESS(1037,"获取注册验证码成功"),


    ACCOUNT_REGISTER(1038,"该帐号已经注册"),
    ACCOUNT_NOT_REGISTER(1039,"该帐号未注册"),
    SEND_VALIDATE_CODE_SUCCESS(1040,"获取验证码成功"),
    SEND_VALIDATE_CODE_FAIL(1041,"获取验证码失败"),


    //格式校验 1060 -- 1079
    FORMAT_PHONE_NUM_ERR (1060,"手机号格式有误"),
    FORMAT_EMAIL_ERR (1061,"邮箱地址格式有误"),



    //INFO 1080-2010
    INFO_RESET_PASSWORD_SUCCESS (1080,"重置密码成功"),
    INFO_RESET_PASSWORD_FAIL (1081,"重置密码失败"),
    QUERY_USER_INFO_SUCCESS (1082,"查询用户信息成功"),
    QUERY_USER_INFO_FAIL (1083,"查询用户信息失败"),
    HEADER_FILE_NULL(1084,"上传的图片为空"),
    HEADER_FILE_SUCCESS(1085,"上传的图片成功"),
    INFO_RESET_SUCCESS (1086,"重置信息成功"),

    //用户关系
    FOLLOW_SUCCESS(3010,"关注成功"),
    FOLLOW_FAIL(3011,"关注失败"),

    UN_FOLLOW_SUCCESS(3012,"取消关注成功"),
    UN_FOLLOW_FAIL(3013,"取消关注失败"),

    REMOVE_FOLLOWER_SUCCESS(3014,"移除粉丝成功"),
    REMOVE_FOLLOWER_FAIL(3015,"移除粉丝失败"),

    LIST_FOLLOWER_SUCCESS(3016,"获取粉丝列表成功"),
    LIST_FOLLOWER_FAIL(3017,"获取粉丝列表失败"),

    LIST_FOLLOWEE_SUCCESS(3018,"获取关注者成功"),
    LIST_FOLLOWEE_FAIL(3019,"获取关注者失败"),

}