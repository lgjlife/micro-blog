package com.microblog.authorization.controller;


import com.microblog.authorization.github.GitHubUserInfo;
import com.microblog.authorization.github.GitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

@Slf4j
@Controller
public class GitAuthorizeController {


    @Autowired
    private GitRequest githubRequest;


    @GetMapping("/callback")
    @ResponseBody
    public  void callback(@NotEmpty String code, HttpServletResponse response){

        log.info("授权码:code = " + code);
        String accessToken = githubRequest.getAccessToken(code);

        if(accessToken != null){
            log.info("accessToken = " + accessToken);
            GitHubUserInfo gitHubUserInfo = githubRequest.getUserInfo(accessToken);
            log.info("gitHubUserInfo = " + gitHubUserInfo);

            if(gitHubUserInfo == null){
                try{

                    response.sendRedirect("http://localhost:8081/manager/login");
                }
                catch(Exception ex){
                    log.error(ex.getMessage());
                }
            }
        }
        else {
            try{
                response.sendRedirect("http://localhost:8081/manager/login");
                
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }
        }
        try{
            response.sendRedirect("http://localhost:8081/manager");
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }
}
