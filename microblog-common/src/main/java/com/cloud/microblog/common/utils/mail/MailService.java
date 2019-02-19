package com.cloud.microblog.common.utils.mail;


import javax.mail.MessagingException;

/**
 * @program: swagger
 * @description: 邮件发送服务接口
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-07-04 16:46
 **/
public interface MailService {

    /**
     * @description: 发送普通邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    void sendSimpleMail(MailSenderMsg msg);

    /**
     * @description: 发送Html邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    void sendHtmlMail(MailSenderMsg msg) throws MessagingException;

    /**
     * @description: 发送带附件邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    void sendAttachmentMail(MailSenderMsg msg);
    /**
     * @description: 文本中带静态资源
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    void sendInlineResourceMail(MailSenderMsg msg);


}
