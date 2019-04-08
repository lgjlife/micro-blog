package com.microblog.common.dto;


import lombok.Builder;
import lombok.Data;

/**
 *功能描述 
 * @author lgj
 * @Description  邮件dto
 * @date 4/2/19
*/
@Data
@Builder
public class MailDto {


    /*邮件接收者*/
    private   String to;

    /*邮件标题*/
    private   String  title;

    /*邮件内容*/
    private   String  conent;

    /*邮件类型*/
    private  String type;
}
