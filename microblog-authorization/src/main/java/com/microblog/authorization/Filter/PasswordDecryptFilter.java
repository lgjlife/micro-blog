package com.microblog.authorization.Filter;

import com.microblog.authorization.service.KeyPairService;
import com.microblog.authorization.util.Md5Util;
import com.microblog.authorization.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 *功能描述  对密码进行解密操作
 * 1.原始密码经过哈希之后保存数据库
 * 2.登录的时候前端密码经过RSA的公钥进行加密 (每次登录的时候都是重新生成公钥和私钥)
 * 3.需要在这里使用RSA私钥解密
 * 3.再经过哈希之后重新设置到字段password
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@Order(0)
@Component
public class PasswordDecryptFilter implements Filter {

    private static final  String GRANT_TYPE_KEY = "grant_type";
    private static final  String GRANT_TYPE_VALUE = "password";

    @Autowired
    private KeyPairService keyPairService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        //只有密码登录才会进行重写password字段
        if(GRANT_TYPE_VALUE.equals(request.getParameter(GRANT_TYPE_KEY))){
            //通过HttpServletRequestWrapper重写请求参数
            HttpServletRequestWrapper servletRequestWrapper = new RemodifyPasswordHttpServletRequestWrapper(request);

            filterChain.doFilter(servletRequestWrapper,servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     *功能描述  重写请求参数处理类　
     * 　　　　需要重写getParameterMap()方法
     * @author lgj
     * @Description 　　　
     * @date 　
    */
    private class RemodifyPasswordHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private HttpServletRequest request;

        private Map<String, String[]> params = new HashMap<>();


        public RemodifyPasswordHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;

            init();
        }

        private void init(){

            Enumeration<String> names =   request.getParameterNames();

            String username = null;
            String password = null;

            while (names.hasMoreElements()){
                String name = names.nextElement();

                String value = request.getParameter(name);
                log.info(name + "  " + value);

                if("username".equals(name)){
                    username = value;
                }
                if("password".equals(name)){
                    password = value;
                }

                params.put(name,new String[]{value});
            }
            //前端密码经过rsa　publickey加密，在这里使用私钥解密。获取原始密码
            String dencryptPassword = decryptPassword(username,password);
            //再对密码进行md5,因为数据库存储的是经过md5之后的密码
            params.put("password",new String[]{Md5Util.md5(dencryptPassword)});
        }

        public String decryptPassword(String username,String password){

            PrivateKey privateKey = keyPairService.queryPrivatekey(username);

            String dencryptPassword = RSAUtils.dencrypt(password,privateKey);

            log.info("解密后的原始密码:" + dencryptPassword);
            return dencryptPassword;

        }


        @Override
        public Map<String, String[]> getParameterMap() {

            return params;
        }

    }

}
