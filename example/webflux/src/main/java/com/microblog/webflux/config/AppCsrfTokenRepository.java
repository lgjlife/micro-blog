package com.microblog.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AppCsrfTokenRepository implements CsrfTokenRepository {

    private ConcurrentHashMap<HttpServletRequest,CsrfToken> csrfTokenConcurrentHashMap;

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        log.info("generateToken");

        CsrfToken csrfToken = new CsrfTokenCreate();

        return csrfToken;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("saveToken");
        csrfTokenConcurrentHashMap.put(httpServletRequest,csrfToken);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {


        log.info("loadToken");
        CsrfToken csrfToken = csrfTokenConcurrentHashMap.get(httpServletRequest);
        log.info("loadToken = " + csrfToken);
        return csrfToken;
    }


    private class  CsrfTokenCreate implements CsrfToken{

        @Override
        public String getHeaderName() {
            return "HeaderName";
        }

        @Override
        public String getParameterName() {
            return "ParameterName";
        }

        @Override
        public String getToken() {
            return UUID.randomUUID().toString();
        }
    }
}
