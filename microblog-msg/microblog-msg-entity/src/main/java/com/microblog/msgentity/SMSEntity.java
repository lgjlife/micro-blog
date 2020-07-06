package com.microblog.msgentity;

import lombok.Builder;
import lombok.Data;

/**
 *功能描述
 * @author lgj
 * @Description  消息中间件短信传输实体　　　
 * @date 　
*/


@Data
@Builder
public class SMSEntity {

    private long id;
    /*短信类型*/
    private SMSType type;

    private String phone;
    /*验证码*/
    private int code;
}
