package com.microblog.common.aop.usetime.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/** 
 * @description:  Controller 层打印请求路径切面类
 *                1.spring-mvc 配置文件添加切面注解支持 <aop:aspectj-autoproxy proxy-target-class="true"/>
 *                2.在Controller上添加注解@PrintUseTimeAnno
 * @param:
 * @return:
 * @author: Mr.lgj 
 * @date: 9/7/18 
*/ 
@Aspect
//@Component
@Slf4j
public class PrintUseTimeAspect {



	/**
	 * 定义切点
	 * execution语法：
	 * （<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>）
	 * 1.通过方法	签名定义切点： （public * *(..)）;匹配所有目标类的public 方法
	 * 2.通过类定义切点 ： （* com.lanmei.user+.*(..)）:匹配user接口的所有方法，有+表示也匹配其 实现类
	 * 3.通过类包定义切点： （* com.lanmei.user.*(..)）匹配com.lanmei.user包下的所有类的所有方法
	 * 					 （* com.lanmei.user..*(..)）匹配com.lanmei.user包/子孙包下的所有类的所有方法
	 * 4.通过方法入参定义切点 * admin(String. *)）匹配方法admin的地一个参数为String ,第二个参数为任意类型的方法
	 */
	/*匹配 com.lanmei.os.controller本包及其子孙包的所有方法*/
//	@Pointcut(value = "execution(* com.lanmei.*.controller..*(..))")
	@Pointcut(value = "@annotation(com.microblog.common.aop.usetime.anno.PrintUseTimeAnno)")
	public void UseTimeLogPointcut() {
		
	}


	@Around(value="UseTimeLogPointcut()")
	public Object doAround(ProceedingJoinPoint invocation)  throws Throwable{

		String methodName = invocation.getSignature().getName();

		Date start = new Date();

		Object result = invocation.proceed();
		Date end = new Date();

		log.debug("方法：{} 消耗时间 ={} ms",methodName,(end.getTime() - start.getTime()));

		return  result;
	}


}
