package com.microblog.points.service.auth;

import com.microblog.common.auth.AuthInterceptorAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *功能描述
 * @author lgj
 * @Description  登录拦截路径配置
 * @date 5/29/19
*/
@Component
public class PointsAuthInterceptorAdapter extends AuthInterceptorAdapter {

    private final  String prePath = "/points";

    @Override
    protected List<String> authPath() {
        List<String> paths = new ArrayList<>();
        paths.add(prePath + "/points/signature/**");
        paths.add(prePath + "/points/signature");
        return paths;
    }

    @Override
    protected String applicationName() {
        return "microblog-points";
    }
}
