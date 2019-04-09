package com.microblog.blog.service.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LogKafkaFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {

        String message = iLoggingEvent.getMessage();

        System.out.println("LogKafkaFilter message = " + message);

        /**
         * 此处是业务代码，可根据自己 的业务代码实现
         */
        /*if (StringUtils.isNotBlank(message)) {
　　　　　　　
            JSONObject auditLog = JSON.parseObject(message);
            log.info("responseBody:" + auditLog.get("responseBody").toString());
            JsonResult jsonResult = JSON.parseObject(auditLog.get("responseBody").toString(), JsonResult.class);
            if (auditLog.get("serviceId").toString().startsWith(RmcloudConstant.SERVICE_ID_RMCLOUD_START)) {
                return FilterReply.ACCEPT;
            }

        }*/
        return FilterReply.ACCEPT;
       // return FilterReply.DENY;
    }

}
