package com.microblog.msgservice.sender;

import com.microblog.msgentity.MailEntity;
import com.microblog.msgservice.exception.MsgSendFailException;
import com.microblog.msgservice.mail.MailSendService;
import com.microblog.msgservice.mail.MailSenderMsg;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DefaultMailSender extends MailSender{

    private MailSendService mailSendService;

    public DefaultMailSender(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    @Override
    public void send(MailEntity mailEntity) throws MsgSendFailException {

        MailSenderMsg mailSenderMsg = new MailSenderMsg(mailEntity.getSendTo(),mailEntity.getType().getSubject(),mailEntity.getContent());

        try{

            mailSendService.sendHtmlMail(mailSenderMsg);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            throw new MsgSendFailException("邮件发送失败");
        }
    }
}
