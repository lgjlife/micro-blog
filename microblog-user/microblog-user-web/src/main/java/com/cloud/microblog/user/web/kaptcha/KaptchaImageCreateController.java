package com.cloud.microblog.user.web.kaptcha;

import com.cloud.microblog.common.aop.syslog.anno.PrintUrlAnno;
import com.cloud.microblog.user.service.constants.UserRedisKeyUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Slf4j
@Controller
@RequestMapping
public class KaptchaImageCreateController {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

	private Producer kaptchaProducer=null;  
	  
    @Autowired
    public void setCaptchaProducer(Producer kaptchaProducer) {  
        this.kaptchaProducer = kaptchaProducer;  
    }

    @ApiOperation(value="/kaptcha",httpMethod="GET",notes = "获取验证码图片")
    @PrintUrlAnno(description = "获取验证码图片")
    @GetMapping("/kaptcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");

        String userName = request.getParameter("userName");
        log.debug("userName = {}",userName);

        String verificationCode = kaptchaProducer.createText();
        log.debug("验证码 = " +  verificationCode);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, verificationCode);


        redisTemplate.opsForValue().set(UserRedisKeyUtil.IMG_VERIF_CODE_KEY.getPrefix()+userName,
                verificationCode,
                UserRedisKeyUtil.IMG_VERIF_CODE_KEY.getTimeout());

        BufferedImage bi = kaptchaProducer.createImage(verificationCode);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
}
