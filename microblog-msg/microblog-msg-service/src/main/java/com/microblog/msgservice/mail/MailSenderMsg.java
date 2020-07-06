package com.microblog.msgservice.mail;


import lombok.Data;

/**
 * @program: mail
 * @description: 邮件发送pojo
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-07-04 16:51
 **/

@Data
public class MailSenderMsg {

    //收件人邮箱地址
    private String  toMail;
    //邮件主题
    private   String  subject;
    //邮件内容
    private  String content;
    //邮件附件
    private  String attachmentFilePath;
    //嵌入的静态资源id
    String rscId;
    //嵌入的静态资源path
    private  String rscFilePath;

    public MailSenderMsg(String toMail, String subject, String content) {
        this.toMail = toMail;
        this.subject = subject;
        this.content = content;
    }

    public MailSenderMsg(String toMail, String subject, String content, String filePath) {
        this.toMail = toMail;
        this.subject = subject;
        this.content = content;
        this.attachmentFilePath = filePath;
    }

    public MailSenderMsg(String toMail, String subject, String content, String filePath, String rscId,String rscFilePath) {
        this.toMail = toMail;
        this.subject = subject;
        this.content = content;
        this.attachmentFilePath = filePath;
        this.rscId = rscId;
        this.rscFilePath = rscFilePath;
    }

}