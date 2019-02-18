package com.cloud.microblog.gateway.service.user;

import com.cloud.microblog.common.utils.encrypt.rsa.RSAKeyFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Slf4j
@Service
public class IUserService  implements  UserService{

    /**
     *功能描述
     * @author lgj
     * @Description   发送验证码到电话
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public void sendPhoneVerificationCode(String code) {

    }

    /**
     *功能描述
     * @author lgj
     * @Description   发送验证码到邮箱
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public void sendEmailVerificationCode(String code) {

    }

    /**
     *功能描述
     * @author lgj
     * @Description   获取RSA 的 modulus   exponent
     * @date 2/18/19
     * @param:
     * @return: Map
     *
    */
    @Override
    public Map getRsaKey() {

        KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) key.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)key.getPrivate();
        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);


        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        log.debug("set key ");
        session.setAttribute("RSA:KeyPair",key);

        Map map = new HashMap();
        map.put("modulus",modulus);
        map.put("exponent",exponent);

        return map;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取6位数字随机码
     * @date 2/18/19
     * @param:
     * @return:
     *
    */
    private  String getCode(){

       return String.valueOf(new Random().nextInt(999999));
    }
}
