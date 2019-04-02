package com.cloud.microblog.user.web.kaptcha;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha kaptcha(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        InputStream fis  = null;
        Properties properties =null;
        try{
            fis = this.getClass().getResourceAsStream("/kaptcha.properties");
            properties = new Properties();
            properties.load(fis);
            log.debug("kaptcha.image.width  = " + properties.get("kaptcha.image.width")); ;
            Config config = new Config(properties);
            kaptcha.setConfig(config);
        }
        catch(Exception ex){
            ex.printStackTrace();
            try{
                fis.close();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        //kaptcha-property.properties





        return  kaptcha;

    }

}
