package com.cloud.frame.cloudzuul.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: top-parent
 * @description:  zuul 熔断器处理
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-17 15:27
 **/
@Component
public class MyFallbackProvider implements FallbackProvider {

    private static  final Logger log = LoggerFactory.getLogger(MyFallbackProvider.class);

    @Override
    public String getRoute() {
        log.info("getRoute....");
        //使用熔断器的名称，如果用于全部，则使用"*"
        return "Auth-User";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("遭遇错误，熔断器正在起作用".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
