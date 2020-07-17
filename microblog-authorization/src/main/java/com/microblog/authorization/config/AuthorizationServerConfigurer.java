package com.microblog.authorization.config;

import com.microblog.authorization.config.handler.ExceptioHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 *功能描述  认证服务器配置
 * @author lgj
 * @Description 　　　
 * @date 　
*/
@Slf4j
@EnableAuthorizationServer
@Configuration
@AllArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailManger;

    /**
     * 配置客户端认证的参数　必须配置三个参数　client_id　，　secret　，　authorizedGrantTypes
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //客户端配置
        //配置获取令牌的方式
        //http://localhost:8081/oauth/token
        // ?grant_type=password
        // &client_id=test-client
        // &client_secret=test-secret
        // &username=my-username
        // &password=my-password


        clients
                //针对管理页面认证
                .inMemory()
                .withClient("manager-client")
                .secret(passwordEncoder.encode("manager-secret"))
                //开放密码方式认证和刷新token功能
                .authorizedGrantTypes(AuthorizedGrantTypes.REFRESH_TOKEN, AuthorizedGrantTypes.PASSWORD)
                //可以设置access_token的超时时间。会在设置的时间基础上添加60s,也就是设置40s，但真正的超时时间是60s+40s
               // .accessTokenValiditySeconds(1)
                .scopes("manager-scope")
                .and()
                //针对正常页面登录
                .inMemory()
                .withClient("normal-client")
                .secret(passwordEncoder.encode("normal-secret"))
                .authorizedGrantTypes(AuthorizedGrantTypes.REFRESH_TOKEN, AuthorizedGrantTypes.PASSWORD)
                //可以设置access_token的超时时间。会在设置的时间基础上添加60s,也就是设置40s，但真正的超时时间是60s+40s
                .scopes("normal-scope")

                //第三方
                .and()
                .inMemory()
                .withClient("third-client")
                .secret(passwordEncoder.encode("third-secret"))
                .authorizedGrantTypes(AuthorizedGrantTypes.REFRESH_TOKEN, AuthorizedGrantTypes.PASSWORD)
                //可以设置access_token的超时时间。会在设置的时间基础上添加60s,也就是设置40s，但真正的超时时间是60s+40s
                .scopes("normal-scope")

                ;

    }

    /**
     * 配置认证过程的处理类，比如异常处理，用户管理
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        endpoints
                //用户管理，需要实现通过用户名从数据库或者其他地方获取到用户信息
                .userDetailsService(userDetailManger)
                .authenticationManager(authenticationManagerBean)
                .accessTokenConverter(accessTokenConverter())
                .exceptionTranslator(new ExceptioHandler())
                ;

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //配置获取令牌的权限
        security
                .allowFormAuthenticationForClients();
    }



    /**
     * 扩展　accesstoken的信息
     * @return
     */
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {

            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

                // ((DefaultOAuth2AccessToken)accessToken).setExpiration(new Date(2019,6,5,6,26));
                // ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation();
                return super.enhance(accessToken, authentication);
            }
        };

        jwtAccessTokenConverter.setKeyPair(keyPair() );
        return jwtAccessTokenConverter;
    }

    /**
     * jwt加解密的密钥，在授权服务器使用私钥加密jwt(header和payload加密之后获得签名)
     * 在资源服务器，这个项目是gateway,认证通过之后第一次访问会从授权服务器获取公钥，资源服务器使用公钥对签名进行解密，
     * 再和客户端发送的jwt进行对比，从而校验jwt是否被更改
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        //获取keypair

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("demojwt.jks"), "keystorepass".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "keypairpass".toCharArray());

        //return RsaKeypairCreator.create();
    }

    /**
     *功能描述 客户端认证类型
     * @author lgj
     * @Description 　　　
     * @date 　
    */
    private static class  AuthorizedGrantTypes{

        /*密码模式*/
        public static final  String PASSWORD = "password";
        /*简化模式*/
        public static final  String IMPLICIT = "implicit";
        /*授权码模式*/
        public static final  String AUTHORIZATION_CODE = "authorization_code";
        /*客户端模式*/
        public static final  String CLIENT_CREDENTIALS = "client_credentials";
        public static final  String REFRESH_TOKEN = "refresh_token";

    }
}
