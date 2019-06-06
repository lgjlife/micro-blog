package com.microblog.user.service.auth;

import com.microblog.common.auth.AuthInterceptorAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthInterceptorAdapter extends AuthInterceptorAdapter {

    @Override
    protected List<String> authPath() {
        List<String> paths = new ArrayList<>();
        paths.add("/user/user/info");
        paths.add("/user/user/info/**");
        return paths;
    }

    @Override
    protected String applicationName() {

        return "microblog-user";
    }
}
