package com.microblog.common.dto;


import java.io.Serializable;

/**
 *功能描述 
 * @author lgj
 * @Description  邮件dto
 * @date 4/2/19
*/

public class MailDto implements Serializable {


    private   long id;
    private   long timeStamp;

    /*邮件接收者*/
    private   String to;

    /*邮件标题*/
    private   String  title;

    /*邮件内容*/
    private   String  conent;

    /*邮件类型*/
    private  String type;

    private   Integer effectiveTimeMs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEffectiveTimeMs() {
        return effectiveTimeMs;
    }

    public void setEffectiveTimeMs(Integer effectiveTimeMs) {
        this.effectiveTimeMs = effectiveTimeMs;
    }

    @Override
    public String toString() {
        return "MailDto{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", to='" + to + '\'' +
                ", title='" + title + '\'' +
                ", conent='" + conent + '\'' +
                ", type='" + type + '\'' +
                ", effectiveTimeMs=" + effectiveTimeMs +
                '}';
    }
}
