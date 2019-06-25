package com.microblog.blog.service.auth;

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
public class BlogAuthInterceptorAdapter extends AuthInterceptorAdapter {

    private final  String prePath = "/blog";

    @Override
    protected List<String> authPath() {
        List<String> paths = new ArrayList<>();
        paths.add(prePath + "/blog/submit/**");
        paths.add(prePath + "/blog/submit");
        paths.add(prePath + "/blog/list");
        return paths;
    }

    @Override
    protected String applicationName() {
        return "microblog-blog";
    }
}
