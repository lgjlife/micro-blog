package com.microblog.common.dto;


/**
 *功能描述 
 * @author lgj
 * @Description  邮件dto
 * @date 4/2/19
*/

public class PhoneVerifCodeDto {

    private   long id;
    private   long timeStamp;
    private   String phone;
    private   String code;
    private   int type;


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
}
