package com.microblog.log.anno;


import com.microblog.log.config.AppenderAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;




@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({AppenderAutoConfiguration.class})
public @interface EnableKafkaLog {

}
