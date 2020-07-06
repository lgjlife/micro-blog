package com.microblog.msgentity;

/**
 *功能描述   邮件类型
 * @author lgj
 * @Description
 * @date
*/


public enum MailType {
    /*邮箱验证*/
    MAIL_VERIFY_TYPE("microblog邮箱验证");

    private String  subject;

    MailType(String subject) {
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }
}
