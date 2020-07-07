package com.microblog.buysbusiness.feign.cart;

import com.microblog.util.response.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartFeignServiceFallbackFactory implements FallbackFactory<CartFeignService> {

    @Override
    public CartFeignService create(Throwable throwable) {
        return new CartFeignService(){

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
