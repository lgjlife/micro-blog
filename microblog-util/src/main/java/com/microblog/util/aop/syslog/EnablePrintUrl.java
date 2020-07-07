package com.microblog.util.aop.syslog;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PrintUrlConfig.class)
public @interface EnablePrintUrl {
}
