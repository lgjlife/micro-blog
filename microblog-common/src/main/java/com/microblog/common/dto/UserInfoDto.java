package com.microblog.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {

    private Long userId;
    private Long userNumber;
    private String nickName;
    private String actualName;
    private String gender;
    private Byte age;
    private String headerUrl;
    private Byte status;
    private String email;
    private Byte emailActive;
    private String phoneNum;
    private Date registerTime;
    private Date lastLoginTime;
    private Date updateTime;
    private Integer loginNums;

    private Long points;

}
