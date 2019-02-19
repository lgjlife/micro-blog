package com.cloud.microblog.gateway.service.shiro.realm;

import com.cloud.microblog.common.utils.UserRegexUtil;
import com.cloud.microblog.gateway.dao.mapper.UserMapper;
import com.cloud.microblog.gateway.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *功能描述 
 * @author lgj
 * @Description   Shiro 登录和鉴权 Realm
 * @date 2/16/19
*/

@Slf4j
//@Component
public class ShiroRealm extends AuthorizingRealm {

    //@Autowired
    UserMapper userMapper;

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
        String loginName = authenticationToken.getPrincipal().toString();
        String password = authenticationToken.getCredentials().toString();
        log.debug("用户：{},密码:{}",loginName,password);
        User user = null;
        if(UserRegexUtil.isMobile(loginName)){
            user = userMapper.selectByPhone(loginName);
        }
        else if(UserRegexUtil.isEmail(loginName)){
            user = userMapper.selectByEmail(loginName);
        }
        if(user == null){
            throw  new UnknownAccountException();
        }

        log.debug("Salt = {},Password = {},loginName = {}",user.getSalt(),user.getLoginPassword(),loginName);

        String resultPassword = new Md5Hash(password,user.getSalt(),3).toString();
        log.debug("密码:{},进行MD5加密的密码={}",password, resultPassword);

        SimpleAuthenticationInfo authenticationInfo
                = new SimpleAuthenticationInfo(user,user.getLoginPassword(),
                ByteSource.Util.bytes(user.getSalt()),loginName);
        return authenticationInfo;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
