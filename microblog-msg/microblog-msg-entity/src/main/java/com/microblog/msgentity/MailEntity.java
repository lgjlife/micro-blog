package com.microblog.msgentity;

import lombok.Builder;
import lombok.Data;

/**
 *功能描述
 * @author lgj
 * @Description  消息中间件邮件传输实体　　　
 * @date 　
*/

@Data
@Builder
public class MailEntity {

    private long id;
    /*短信类型*/
    private MailType type;
    /*邮件发送地址*/
    private String sendTo;

    /*邮件内容*/
    private String content;


}
