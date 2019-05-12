package com.microblog.common.dto;


import lombok.Builder;
import lombok.Data;

/**
 *功能描述 
 * @author lgj
 * @Description  邮件dto
 * @date 4/2/19
*/
@Data
@Builder
public class PhoneVerifCodeDto {

    private   long id;
    private   long timeStamp;
    private   String phone;
    private   String code;
    private   int type;


}
