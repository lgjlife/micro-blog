package com.microblog.msg.mail;


/**
 * @program: mail
 * @description: 邮件发送pojo
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-07-04 16:51
 **/
public class MailSenderMsg {

    //收件人邮箱地址
    private String  toMail;
    //邮件主题
    private   String  subject;
    //邮件内容
    private  String text;
    //邮件附件
    private  String attachmentFilePath;
    //嵌入的静态资源id
    String rscId;
    //嵌入的静态资源path
    private  String rscFilePath;

    public MailSenderMsg(String toMail, String subject, String text) {
        this.toMail = toMail;
        this.subject = subject;
        this.text = text;
    }

    public MailSenderMsg(String toMail, String subject, String text, String filePath) {
        this.toMail = toMail;
        this.subject = subject;
        this.text = text;
        this.attachmentFilePath = filePath;
    }

    public MailSenderMsg(String toMail, String subject, String text, String filePath, String rscId,String rscFilePath) {
        this.toMail = toMail;
        this.subject = subject;
        this.text = text;
        this.attachmentFilePath = filePath;
        this.rscId = rscId;
        this.rscFilePath = rscFilePath;
    }

    public String getToMail() {
        return toMail;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }

    public String getRscId() {
        return rscId;
    }

    public String getRscFilePath() {
        return rscFilePath;
    }
}