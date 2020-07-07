package com.microblog.buysbusiness.feign.goods;

import com.microblog.util.response.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class GoodsFeignServiceFallback implements GoodsFeignService {


    @Override
    public ServerResponseDto query() {
        return null;
    }

    @Override
    public ServerResponseDto delete() {
        log.error("调用发生错误");
        return new ServerResponseDto(ResponseCode.FAIL.getCode()
                ,ResponseCode.FAIL.getMessage());
    }
}
