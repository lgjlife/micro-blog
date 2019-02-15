package com.cloud.frame.cloudzuul.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: top-parent
 * @description: 解决 No qualifying bean of type 'org.springframework.core.env.ConfigurableEnvironment'
 *      available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
 * @author: Mr.lgj
 * @version:
 * @See:  https://blog.csdn.net/annotation_yang/article/details/82877433
 * @create: 2018-11-19 18:23
 **/
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}
