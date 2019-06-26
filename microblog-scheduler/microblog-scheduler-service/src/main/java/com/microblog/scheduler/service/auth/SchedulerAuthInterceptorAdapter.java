package com.microblog.scheduler.service.auth;

import com.microblog.common.auth.AuthInterceptorAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerAuthInterceptorAdapter extends AuthInterceptorAdapter {

    @Override
    protected List<String> authPath() {
        List<String> paths = new ArrayList<>();
        return paths;
    }

    @Override
    protected String applicationName() {

        return "microblog-scheduler";
    }
}
