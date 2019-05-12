package com.microblog.common.dto;

import java.io.Serializable;

//@Data
public class SMSDto implements Serializable {

    private   long id;
    private   long timeStamp;
    private   String phone;
    private   String code;
    private   int type;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        return "SMSDto{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", effectiveTimeMs=" + effectiveTimeMs +
                '}';
    }
}
