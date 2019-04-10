package com.microblog.log.anno;


import java.lang.annotation.*;




@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableKafkaLog {

    String brokerAddress() default  "localhost:9092";
    String applicationName() default  "EnableKafkaLog";
}
