package com.microblog.msgservice.sender;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.microblog.msgservice.exception.MsgSendFailException;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class DefaultSmsSender extends SmsSender{


    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIZ9vHdEp2HHeB";
    static final String accessKeySecret = "T8zg5xPgXNI1EIyYQdTOYRfckzBf9i";

    private SendSmsRequest request;
    private IAcsClient acsClient;

    public DefaultSmsSender(){
        init();
    }

    public void init(){
        try{

            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
            //组装请求对象-具体描述见控制台-文档部分内容
            request = new SendSmsRequest();
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }

    //耗时1803ms
    public void  send(String phoneNum,Object content) throws MsgSendFailException {

        // request.setMethod(MethodType.POST);
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("蓝莓");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_130916740");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{ \"code\":\"" + content +  "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutIdaa");

        log.debug("发送短信");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = null;
        try {
            response = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(response.getCode() != null)
        {
            if(response.getCode() == "OK"){
                log.debug("短信{}发送成功",phoneNum);
            }
            else {

                log.error("短信发送失败:[phone:{},code={}.msg={}]",phoneNum,response.getCode(),response.getMessage());
                throw  new MsgSendFailException(response.getMessage());
            }

        }



    }


    public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

}