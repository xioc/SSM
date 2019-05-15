package com.ssm.chapter11.aspect;
import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class ProxyFactoryBeanAspect implements MethodBeforeAdvice {

	@Override
	/***
	 * 前置通知
	 * @param method 被拦截方法（切点）
	 * @param params 参数 数组[role]
	 * @param roleService 被拦截对象
	 */
	public void before(Method method, Object[] params, Object roleService) throws Throwable {
		System.out.println("前置通知！！");
	}
}