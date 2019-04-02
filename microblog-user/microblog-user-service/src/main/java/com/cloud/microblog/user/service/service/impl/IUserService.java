package com.cloud.microblog.user.service.service.impl;


import com.cloud.microblog.common.aop.usetime.anno.PrintUseTimeAnno;
import com.cloud.microblog.common.code.UserReturnCode;
import com.cloud.microblog.common.dto.MailDto;
import com.cloud.microblog.common.dto.PhoneVerifCodeDto;
import com.cloud.microblog.common.result.BaseResult;
import com.cloud.microblog.common.token.jwt.JWTClaimsKey;
import com.cloud.microblog.common.token.jwt.JwtUtil;
import com.cloud.microblog.common.utils.UserRegexUtil;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAKeyFactory;
import com.cloud.microblog.common.utils.encrypt.rsa.RSAUtil;
import com.cloud.microblog.user.dao.mapper.UserMapper;
import com.cloud.microblog.user.dao.model.User;
import com.cloud.microblog.user.service.config.utils.RedisStringUtil;
import com.cloud.microblog.user.service.constants.UserRedisKeyUtil;
import com.cloud.microblog.user.service.rabbitmq.PublishConfig;
import com.cloud.microblog.user.service.rabbitmq.RabbitmqProducer;
import com.cloud.microblog.user.service.rocketmq.RocketmqProducer;
import com.cloud.microblog.user.service.rocketmq.RocketmqPublishConfig;
import com.cloud.microblog.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *功能描述 
 * @author lgj
 * @Description  用户模块实现
 * @date 2/19/19
*/
@Slf4j
@Service
public class IUserService implements UserService {

    @Autowired
    UserMapper userMapper;

  //  @Autowired
  //  MailService mailService;

    @Autowired
    RedisStringUtil redisStringUtil;

    @Autowired
    RabbitmqProducer  rabbitmqProducer;


    @Autowired
    RocketmqProducer rocketmqProducer;


   // @Autowired
  //  TokenClient  tokenClient;

    private  PublishConfig mailPublishConfig =  PublishConfig.builder()
            .exchangeName("USER_MAIL_EXCHANGE_NAME")
            .queueName("USER_MAIL_QUEUE_NAME")
            .bingKey("USER_MAIL_ROUTING_KEY")
            .routingkey("USER_MAIL_ROUTING_KEY")
            .build();

    private RocketmqPublishConfig phonePublishConfig = RocketmqPublishConfig.builder()
            .topic("PHONE_VERIFY_CODE_TOPIC")
            .tags("PHONE_VERIFY_CODE_TAGS")
            .build();

    //RedisTemplate<String, Object> redisTemplate;
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
    public boolean sendPhoneVerificationCode(String phone) {
        String code = getCode();
        try{
            // TODO  使用消息中间件处理
            Date start = new Date();
         //   SmsUtil.sendSms(phone,code);

            PhoneVerifCodeDto phoneVerifCodeDto = PhoneVerifCodeDto.builder().phone(phone).code(code).build();
            rocketmqProducer.publish(phoneVerifCodeDto,phonePublishConfig);
            Date end = new Date();
            log.debug("向手机号({})发送验证码:({})",phone,code);
            log.info("短信花费时间：" + (end.getTime() - start.getTime()));
            redisStringUtil.set(UserRedisKeyUtil.VERIF_CODE_KEY.getPrefix()+phone,
                    code,
                    UserRedisKeyUtil.VERIF_CODE_KEY.getTimeout());

        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  true;


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
    public boolean sendEmailVerificationCode(String email) {
        String code = getCode();
        try{
            String content = "本次验证码为：" + code+"." ;
            Date start = new Date();
            // TODO  使用消息中间件处理
            MailDto mailDto = MailDto.builder().to(email)
                    .title("Micro-Blog 验证码")
                    .conent(content)
                    .build();
            rabbitmqProducer.publish(mailDto,mailPublishConfig);
            log.debug("向邮箱({})发送验证码:({})",email,code);
            Date end = new Date();
            log.info("邮件花费时间：" + (end.getTime() - start.getTime()));
            redisStringUtil.set(UserRedisKeyUtil.IMG_VERIF_CODE_KEY.getPrefix()+email,
                    code,
                    UserRedisKeyUtil.IMG_VERIF_CODE_KEY.getTimeout());
        }
        catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  true;

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
    @PrintUseTimeAnno
    // TODO 第一次花费2.368s,后面11ms
    @Override
    public Map getRsaKey(String name) {

        KeyPair keyPair = RSAKeyFactory.getInstance().getKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        String modulus = publicKey.getModulus().toString(16);
        String exponent = publicKey.getPublicExponent().toString(16);


        String privateModulus = privateKey.getModulus().toString();
        String privateExponent = privateKey.getPrivateExponent().toString();

        redisStringUtil.set(UserRedisKeyUtil.KEYPAIR_PRIVATE_MODULUS_KEY.getPrefix()+name,
                privateModulus,
                UserRedisKeyUtil.KEYPAIR_PRIVATE_MODULUS_KEY.getTimeout());

        redisStringUtil.set(UserRedisKeyUtil.KEYPAIR_PRIVATE_EXPONENT_KEY.getPrefix()+name,
                privateExponent,
                UserRedisKeyUtil.KEYPAIR_PRIVATE_EXPONENT_KEY.getTimeout());
        Map map = new HashMap();
        map.put("modulus",modulus);
        map.put("exponent",exponent);

        return map;
    }



    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱登录
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public BaseResult login(String name, String encPassword) {

        User user = null;
        //获取原始密码
        String privateModulus =  (String)redisStringUtil.get(UserRedisKeyUtil.KEYPAIR_PRIVATE_MODULUS_KEY.getPrefix()+name);
        String privateExponent =  (String)redisStringUtil.get(UserRedisKeyUtil.KEYPAIR_PRIVATE_EXPONENT_KEY.getPrefix()+name);

        RSAPrivateKey privateKey = null;

        try{
            privateKey =  RSAUtil.generateRSAPrivateKey(new BigInteger(privateModulus).toByteArray(),
                    new BigInteger(privateExponent).toByteArray());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        String password = decPassword(encPassword,privateKey);
        log.debug("原始密码 = " + password);

        if(UserRegexUtil.isMobile(name)){
            user = userMapper.selectByPhone(name);
        }
        else if(UserRegexUtil.isEmail(name)){
            user = userMapper.selectByEmail(name);
        }

        //帐号未注册
        if(user == null){
            return new BaseResult(UserReturnCode.ACCOUNT_NOT_REGISTER.getCode(),UserReturnCode.ACCOUNT_NOT_REGISTER.getMessage());
        }


        String random= user.getSalt();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String resultPassword = new Md5Hash(password,random,3).toString();


        if(user.getLoginPassword().equals(resultPassword)){
            log.debug("{}-密码验证正确,用户登录成功");
        }

        //远程获取token
       // String token = tokenClient.queryToken(user.getUserId());

        String token =  createToken(user.getUserId());
        log.debug("token.length={},token = {}",token.length(),token);
        return new BaseResult(UserReturnCode.LOGIN_SUCCESS.getCode(),
                UserReturnCode.LOGIN_SUCCESS.getMessage(),
                token);
    }

    public  String createToken( Long id){

        Map<String,String> claims = new HashMap<>();
        claims.put(JWTClaimsKey.userId,String.valueOf(id));
        String  token =  JwtUtil.createJwt(claims);
        log.debug("id = {},token = {} ",id,token);

        /*try{
            boolean result = JwtUtil.verify(token);
            log.debug("JWTUtil.verify result = {}",result);

             result = JwtUtil.verify(token);
            log.debug("JWTUtil.verify result = {}",result);

             result = JwtUtil.verify(token);
            log.debug("JWTUtil.verify result = {}",result);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        return  token;
    }



    /**
     *功能描述
     * @author lgj
     * @Description 退出登录
     * @date 2/19/19
     * @param:
     * @return:
     *
    */
    @Override
    public UserReturnCode logout() {



        return  UserReturnCode.LOGOUT_SUCCESS;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  通过电话/邮箱注册
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    @Override
    public UserReturnCode register(String name,String encPassword) {

        User user = null;
        log.debug("{}正在注册.....",name);

        //检测是否已经注册
        if(UserRegexUtil.isMobile(name)){
            if(userMapper.selectIdByPhone(name) != null){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setPhoneNum(name);
            log.debug("手机帐号({})注册",name);

        }
        else if(UserRegexUtil.isEmail(name)){
            if(userMapper.selectIdByEmail(name) != null){
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setEmail(name);
            log.debug("手机帐号({})注册",name);
        }
        log.debug(user.toString());

        //获取原始密码
        String privateModulus =  (String)redisStringUtil.get(UserRedisKeyUtil.KEYPAIR_PRIVATE_MODULUS_KEY.getPrefix()+name);
        String privateExponent =  (String)redisStringUtil.get(UserRedisKeyUtil.KEYPAIR_PRIVATE_EXPONENT_KEY.getPrefix()+name);

        RSAPrivateKey privateKey = null;

        try{
            privateKey =  RSAUtil.generateRSAPrivateKey(new BigInteger(privateModulus).toByteArray(),
                    new BigInteger(privateExponent).toByteArray());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        String password = decPassword(encPassword,privateKey);

        //对原始密码加密
        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String resultPassword = new Md5Hash(password,random,3).toString();
        log.debug("进行MD5加密的密码 = " + resultPassword);


        user.setLoginPassword(resultPassword);
        user.setSalt(random);
        user.setRegisterTime(new Date());

        log.debug(user.toString());

        if(userMapper.insert(user) != 0){
            return UserReturnCode.REGISTER_SUCCESS;
        }

        return UserReturnCode.REGISTER_FAIL;

    }

    /**
     *功能描述
     * @author lgj
     * @Description  测试手机邮箱验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    public boolean checkVerificationCode(String name,String code){

        String saveCode = (String) redisStringUtil.get(UserRedisKeyUtil.VERIF_CODE_KEY.getPrefix()+name);
        log.debug("saveCode = {} , code = {}",saveCode , code);
        return  code.equals(saveCode);
    }
    /**
     *功能描述
     * @author lgj
     * @Description   测试图片验证码是否正确
     * @date 2/18/19
     * @param:
     * @return:
     *
     */
    public boolean checkImgVerificationCode(String name,String code){

        String saveCode = (String) redisStringUtil.get(UserRedisKeyUtil.IMG_VERIF_CODE_KEY.getPrefix()+name);

        log.debug("saveCode = {} , code = {}",saveCode , code);
        return  code.equals(saveCode);
    }

    private String decPassword(String encPassword,RSAPrivateKey privateKey ){

        String decPassqord = null;

        log.debug("开始进行解密");


        log.debug("encPassword = {}" ,encPassword);
        log.debug("privateKey={}" ,privateKey );
        byte[] en_result  = RSAUtil.hexStringToBytes(encPassword);//解决Bad arguments问题
        log.debug("en_result len  =  " + en_result.length);
        try{

            byte[] decPasswordByte = RSAUtil.decrypt(privateKey,en_result);

            log.debug("decPasswordByte.len = {}",decPasswordByte.length );
            String passStr = new String(decPasswordByte);
            log.debug("passStr = {}",passStr );
            //翻转字符串
            StringBuffer StrBuf = new StringBuffer();
            StrBuf.append(passStr);
            decPassqord = StrBuf.reverse().toString();

            log.debug("解密后的密码 = " + decPassqord);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return decPassqord;

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


