package com.cloud.microblog.gateway.service.shiro.config;


import com.cloud.microblog.gateway.dao.mapper.UserMapper;
import com.cloud.microblog.gateway.service.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *功能描述
 * @author lgj
 * @Description  shiro 配置类
 * @date 2/16/19
*/
@Configuration
public class ShiroConfig {


    /**
     *功能描述
     * @author lgj
     * @Description  SHIRO  安全管理器  核心管理器
     * @date 2/16/19
     * @param:
     * @return:
     *
    */
    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm,
                                                     CacheManager cacheManager,
                                                   //  CookieRememberMeManager rememberMeManager,
                                                     SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        // securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public Cookie cookie(){
        Cookie cookie = new SimpleCookie("CookieRemember");
        cookie.setHttpOnly(true);
        //cookie 生效时间　，单位s
        cookie.setMaxAge(30*24*60*60);
        return cookie;

    }

    @Bean
    public  CookieRememberMeManager  rememberMeManager(Cookie cookie){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();

        byte[] dd = Base64.decode("2AvVhdsgUs0FSA3SDFAdag==");
        rememberMeManager.setCipherKey(dd);
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }

    @Bean
    public ShiroRealm shiroRealm(HashedCredentialsMatcher matcher, UserMapper userMapper){
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(matcher);
        realm.setUserMapper(userMapper);
        return  realm;
    }


    @Bean
    public HashedCredentialsMatcher credentialsMatcher (){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(3);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return  credentialsMatcher;
    }
    @Bean
    public SimpleCookie  simpleCookie(){
        SimpleCookie simpleCookie   = new SimpleCookie("SimpleCookie");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        return  simpleCookie;
    }


    @Bean
    public DefaultWebSessionManager sessionManager(Cookie cookie,
                                                   ShiroSessionDao sessionDAO){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //全局的会话信息时间,,单位为毫秒
        sessionManager.setGlobalSessionTimeout(30*60*1000);

        //检测扫描信息时间间隔,单位为毫秒-
        sessionManager.setSessionValidationInterval(6000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionDAO(sessionDAO);
        return  sessionManager;

    }

    /*@Bean
    public CachingSessionDAO sessionDAO(){
        CachingSessionDAO sessionDAO = new ShiroSessionDao();
        return  sessionDAO;
    }*/

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return  lifecycleBeanPostProcessor;
    }

    @Bean
    MemoryConstrainedCacheManager cacheManager(){
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        return  cacheManager;
    }


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        filter.setSecurityManager(securityManager);
        //要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/user/Login.html"页面
        filter.setLoginUrl("/user/Login");
        //登录成功后要跳转的连接
        filter.setSuccessUrl("");
        //用户访问未对其授权的资源时,所显示的连接
        filter.setUnauthorizedUrl("");

        /**
         * <!-- Shiro连接约束配置,即过滤链的定义 -->
         * <!-- 此处可配合这篇文章来理解各个过滤连的作用http://blog.csdn.net/jadyer/article/details/12172839 -->
         * <!-- 下面value值的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的 -->
         * <!-- anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 -->
         * <!-- authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter -->
         * <!--
         * anon:例子/admins/**=anon 没有参数，表示可以匿名使用
         * authc:例如/admins/user/**=authc表示需要认证(登录)才能使用，没有参数
         * roles(角色)：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
         * perms（权限）：例子/admins/user/**=perms[user:add:*],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         * rest：例子/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
         * port：例子/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString
         * 是你访问的url里的？后面的参数。
         * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证
         * ssl:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
         * user:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查        -->
         **/
        Map<String, String> filterChainDefinitionMap  =  new HashMap<String, String>();
        filterChainDefinitionMap.put("/**","anon");
        filter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filter;
    }


}
