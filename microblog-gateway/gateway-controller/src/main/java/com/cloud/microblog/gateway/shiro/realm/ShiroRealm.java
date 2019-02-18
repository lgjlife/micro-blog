package com.cloud.microblog.gateway.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *功能描述 
 * @author lgj
 * @Description   Shiro 登录和鉴权 Realm
 * @date 2/16/19
*/

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     *功能描述
     * @author lgj
     * @Description  权限认证
     * @date 2/16/19
     * @param:
     * @return:
     *
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("doGetAuthorizationInfo...........");
        return null;
    }

    /**
     *功能描述
     * @author lgj
     * @Description   用户认证
     * @date 2/16/19
     * @param:
     * @return:
     *
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.debug("doGetAuthenticationInfo...........");
        return null;
    }
}
