package com.microblog.msgservice.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @program: swagger
 * @description: 邮件发送服务接口实现
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-07-04 16:47
 **/

@Slf4j
public class MailSendServiceImpl implements MailSendService {
    private JavaMailSenderImpl javaMailSender;


    public MailSendServiceImpl(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * @description: 发送普通邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    @Override
    public void sendSimpleMail(MailSenderMsg msg) throws MailException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(msg.getToMail());
        message.setFrom(javaMailSender.getUsername());
        message.setSubject(msg.getSubject());
        message.setText(msg.getContent());
        javaMailSender.send(message);
    }
    /**
     * @description: 发送Html邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    @Override
    public void sendHtmlMail(MailSenderMsg msg)  throws MailException,MessagingException{


        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(msg.getToMail());
        helper.setFrom(javaMailSender.getUsername());
        helper.setSubject(msg.getSubject());
        helper.setText(msg.getContent(),true);

        javaMailSender.send(message);
        log.info(msg.getToMail() + "  Html邮件发送成功！");
    }
    /**
     * @description: 发送带附件邮件
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/4/18
     */
    @Override
    public void sendAttachmentMail(MailSenderMsg msg)  throws MailException,MessagingException{


        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(msg.getToMail());
        helper.setFrom(javaMailSender.getUsername());
        helper.setSubject(msg.getSubject());
        helper.setText(msg.getContent(),true);

        String attachmentFilePath = msg.getAttachmentFilePath();
        FileSystemResource file = new FileSystemResource(new File(attachmentFilePath));
        String fileName = attachmentFilePath.substring(attachmentFilePath.lastIndexOf(File.separator));
        //多个附件可以多次调用添加
        helper.addAttachment(fileName,file);
        javaMailSender.send(message);
        log.info(msg.getToMail() + "  带附件邮件发送成功！");
    }

    @Override
    public void sendInlineResourceMail(MailSenderMsg msg) throws MailException,MessagingException {


        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(msg.getToMail());
        helper.setFrom(javaMailSender.getUsername());
        helper.setSubject(msg.getSubject());
        helper.setText(msg.getContent(),true);

        String attachmentFilePath = msg.getAttachmentFilePath();
        FileSystemResource file = new FileSystemResource(new File(attachmentFilePath));
        String fileName = attachmentFilePath.substring(attachmentFilePath.lastIndexOf(File.separator));

        String rscFilePath = msg.getRscFilePath();
        FileSystemResource res = new FileSystemResource(new File(rscFilePath));
        helper.addInline(msg.getRscId(), res);

        //多个附件可以多次调用添加
        helper.addAttachment(fileName,file);
        javaMailSender.send(message);
        log.info(msg.getToMail() + "  带附件，嵌入图片邮件发送成功！");
    }
}