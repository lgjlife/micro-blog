package com.microblog.buysbusiness.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class SeataInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String xid = RootContext.getXID();

        log.debug("xid  = {}",xid);

        requestTemplate.header(RootContext.KEY_XID,xid);


    }


}
