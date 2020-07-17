package com.microblog.authorization.github;


import com.alibaba.fastjson.JSON;
import com.microblog.authorization.util.HumpTool;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *功能描述  git 认证相关请求
 * @author lgj
 * @Description 　　@see <a href="https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/"></a>　
 * @date 　
*/
@Slf4j
@Component
public class GitRequest {

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private GitAuthProperties gitAuthProperties;

    public String getAccessToken(String code){

        String result = null;

        Response response = requestAccessToken(code);
        String accessToken = null;
        if(response != null){
            try{
                result = response.body().string();
                accessToken = decodeRetData(result,new TokenData()).getAccessToken();
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }
        }
        return accessToken;
    }

    public GitHubUserInfo getUserInfo(String accessToken){
        return getUserInfo(accessToken, 10);
    }
    /**
     * 通过accessToken获取userinfo
     * @param accessToken
     */
    public GitHubUserInfo getUserInfo(String accessToken, int timeoutS){


        Request request = new Request.Builder()
                .url(gitAuthProperties.getGetUserInfoUrl())
                .addHeader("Authorization","Bearer " + accessToken)
                .get()
                .build();

        Call call =  okHttpClient.newCall(request);
        call.timeout().timeout(timeoutS, TimeUnit.SECONDS);


        GitHubUserInfo gitHubUserInfo = null;
        try{

            Response response =  call.execute();
            ResponseBody body =  response.body();

             gitHubUserInfo = JSON.parseObject(body.string(), GitHubUserInfo.class);

        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
        return gitHubUserInfo;
    }

    private Response requestAccessToken(String code){

        return requestAccessToken(code,10);
    }

    /**
     * 向github获取access_token
     * @param code　
     *　@return 　　Response or null
     */
    private Response requestAccessToken(String code, int timeoutS){

        HttpUrl.Builder builder = HttpUrl.parse(gitAuthProperties.getAccessTokenUrl()).newBuilder();

        builder.addQueryParameter("client_id", gitAuthProperties.getClientId());
        builder.addQueryParameter("client_secret", gitAuthProperties.getClientSecret());
        builder.addQueryParameter("code", code);

        HttpUrl url = builder.build();

        RequestBody body = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.timeout().timeout(timeoutS, TimeUnit.SECONDS);

        try{

            Response response =  call.execute();
            return response;
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
        return null;

    }





    /*
    access_token=b76ceaee329811c40b342a4e37ce642baf9bea6a
    &expires_in=28800
    &refresh_token=r1.68cd4c61beede597415fad1f88be1d2791aa3a3e3f2f80f7f90915737b605392441e6596ba1ec57a
    &refresh_token_expires_in=15897600
    &scope=
    &token_type=bearer
    * */


    /**
     * 从客户端返回的字符串中解析出对象　: a=xx&b=xxx&c=xxx --> obj{a:xx;b:xxx;c:xxx}
     * @param in
     * @param obj
     * @param <T>
     * @return
     */
    public <T> T decodeRetData(String in,T obj){

        String[] maps = in.split("&");

        for (String map:maps){
            String[] properties = map.split("=");
            if(properties.length > 1){
                try{
                    BeanUtils.setProperty(obj, HumpTool.lineToHump(properties[0]),properties[1]);
                }
                catch(Exception ex){

                }
            }

        }

        return (T)obj;

    }

    public static void main(String args[]){

//        String data = "access_token=b76ceaee329811c40b342a4e37ce642baf9bea6a&expires_in=28800&refresh_token=r1.68cd4c61beede597415fad1f88be1d2791aa3a3e3f2f80f7f90915737b605392441e6596ba1ec57a&refresh_token_expires_in=15897600&scope=&token_type=bearer";
//
//        GitRequest request = new GitRequest();
//        TokenData tokenData =  request.decodeRetData(data,new TokenData());
//
//
//        System.out.println(tokenData);\\\

      //  JSONPObject jsonpObject = new JSONPObject();

        //jsonpObject.

        String json = "{\"login\":\"lgjlife\",\"id\":35231177,\"node_id\":\"MDQ6VXNlcjM1MjMxMTc3\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/35231177?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/lgjlife\",\"html_url\":\"https://github.com/lgjlife\",\"followers_url\":\"https://api.github.com/users/lgjlife/followers\",\"following_url\":\"https://api.github.com/users/lgjlife/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/lgjlife/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/lgjlife/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/lgjlife/subscriptions\",\"organizations_url\":\"https://api.github.com/users/lgjlife/orgs\",\"repos_url\":\"https://api.github.com/users/lgjlife/repos\",\"events_url\":\"https://api.github.com/users/lgjlife/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/lgjlife/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"lgjlife\",\"company\":null,\"blog\":\"https://www.cnblogs.com/lgjlife/\",\"location\":\" ShenZhen ,China\",\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":23,\"public_gists\":0,\"followers\":14,\"following\":0,\"created_at\":\"2018-01-08T16:21:19Z\",\"updated_at\":\"2020-07-16T14:39:44Z\"}";


        GitHubUserInfo gitHubUserInfo = JSON.parseObject(json, GitHubUserInfo.class);

       // System.out.println(gitHubUserInfo);


        GitAuthProperties gitAuthProperties = new  GitAuthProperties();
        OkHttpClient okHttpClient = new OkHttpClient();


        HttpUrl.Builder builder = HttpUrl.parse(gitAuthProperties.getAccessTokenUrl()).newBuilder();

        builder.addQueryParameter("client_id", gitAuthProperties.getClientId());
        builder.addQueryParameter("client_secret", gitAuthProperties.getClientSecret());
        builder.addQueryParameter("code", "2e1731ad7d5b6bb5ddca");

        HttpUrl url = builder.build();

        RequestBody body = new FormBody.Builder().build();


        System.out.println("url = " + url);
        Request request = new Request.Builder()
                .url(url)
                .post(body)

                .build();

        System.out.println("request = " + request);

        Call call = okHttpClient.newCall(request);
        try{

            Response response =  call.execute();

            System.out.println("response = " + response.body().string());
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }




    }









}



