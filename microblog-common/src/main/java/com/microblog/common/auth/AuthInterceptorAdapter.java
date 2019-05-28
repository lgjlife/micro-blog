package com.microblog.common.auth;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AuthInterceptorAdapter {

    private Map<String, List<String>> authPaths = new LinkedHashMap<>();

    private String appName;
    private List<String> paths;

    protected abstract List<String> authPath();

    protected abstract String applicationName();


    public Map<String, List<String>> getAuthPaths() {

        appName = applicationName();
        paths = authPath();
        log.info("appName={},paths={}",appName,paths);
        authPaths.put(appName,paths);
        return authPaths;
    }

    public String getAppName() {
        return applicationName();
    }
}
