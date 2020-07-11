package com.microblog.authorization.config;

import com.microblog.authorization.config.handler.ExceptioHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 授权服务器配置
 */

@Slf4j
@EnableAuthorizationServer
@Configuration
@AllArgsConstructor
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailManger;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //客户端配置
        //配置获取令牌的方式
        //http://localhost:8081/oauth/token
        // ?grant_type=password
        // &&client_id=test-client
        // &&client_secret=test-secret
        // &&username=my-username
        // &&password=my-password


        clients.inMemory()
                .withClient("test-client")
                .secret(passwordEncoder.encode("test-secret"))
                .authorizedGrantTypes("refresh_token", "password")
                //可以设置access_token的超时时间。会在设置的时间基础上添加60s,也就是设置40s，但真正的超时时间是60s+40s
               // .accessTokenValiditySeconds(1)

                .scopes("default-scope");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        endpoints
                .userDetailsService(userDetailManger)
                .authenticationManager(authenticationManagerBean)
                .accessTokenConverter(accessTokenConverter())
                .exceptionTranslator(new ExceptioHandler())
                .exceptionTranslator(new WebResponseExceptionTranslator<OAuth2Exception>() {
                    @Override
                    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

                        ResponseEntity<OAuth2Exception> responseEntity
                                = new ResponseEntity<OAuth2Exception>(HttpStatus.valueOf(400));
                        e.printStackTrace();
                        log.info("translate");
                        return responseEntity;
                    }
                })
        ;

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //配置获取令牌的权限
        security.allowFormAuthenticationForClients();
    }

    //jwt转换器
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {

            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                log.info("accessTokenConverter enhance");

                Date date = ((DefaultOAuth2AccessToken) accessToken).getExpiration();

                String fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                log.info("Expiration + " + fd);

                int ExpiresIn = ((DefaultOAuth2AccessToken) accessToken).getExpiresIn();

                log.info("ExpiresIn = " + ExpiresIn);

                // ((DefaultOAuth2AccessToken)accessToken).setExpiration(new Date(2019,6,5,6,26));
                // ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation();
                return super.enhance(accessToken, authentication);
            }
        };

        jwtAccessTokenConverter.setKeyPair(keyPair() );
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("demojwt.jks"), "keystorepass".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "keypairpass".toCharArray());

        //return KeypairCreator.create();
    }
}
