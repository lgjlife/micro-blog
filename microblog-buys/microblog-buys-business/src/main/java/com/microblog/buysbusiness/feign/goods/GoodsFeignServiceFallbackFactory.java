package com.microblog.buysbusiness.feign.goods;

import com.microblog.util.response.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoodsFeignServiceFallbackFactory implements FallbackFactory<GoodsFeignService> {

    @Override
    public GoodsFeignService create(Throwable throwable) {
        return new GoodsFeignService(){

            @Override
            public ServerResponseDto query() {

                log.error("调用发生错误");
                return new ServerResponseDto(ResponseCode.FAIL.getCode()
                        ,ResponseCode.FAIL.getMessage());
            }

            @Override
            public ServerResponseDto delete() {
                log.error("调用发生错误");
                return new ServerResponseDto(ResponseCode.FAIL.getCode()
                        ,ResponseCode.FAIL.getMessage());
            }

        };
    }
}
