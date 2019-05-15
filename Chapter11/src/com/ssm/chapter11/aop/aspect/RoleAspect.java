package com.ssm.chapter11.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import com.ssm.chapter11.aop.verifier.RoleVerifier;
import com.ssm.chapter11.aop.verifier.impl.RoleVerifierImpl;
import com.ssm.chapter11.game.pojo.Role;

@Aspect
public class RoleAspect {
	
	@DeclareParents(value= "com.ssm.chapter11.aop.service.impl.RoleServiceImpl+", defaultImpl=RoleVerifierImpl.class)
	public RoleVerifier roleVerifier;

	@Pointcut("execution(* com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
	public void print() {
	}

	@Before("print()")
	// @Before("execution(*
	// com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
	public void before() {
		System.out.println("before ....");
	}

	@After("print()")
	// @After("execution(*
	// com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
	public void after() {
		System.out.println("after ....");
	}

	@AfterReturning("print()")
	// @AfterReturning("execution(*
	// com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
	public void afterReturning() {
		System.out.println("afterReturning ....");
	}

	@AfterThrowing("print()")
	// @AfterThrowing("execution(*
	// com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..))")
	public void afterThrowing() {
		System.out.println("afterThrowing ....");
	}

	@Around("print()")
	public void around(ProceedingJoinPoint jp) {
		System.out.println("around before ....");
		try {
			jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("around after ....");
	}

	@Before("execution(* com.ssm.chapter11.aop.service.impl.RoleServiceImpl.printRole(..)) " + "&& args(role, sort)")
	public void before(Role role, int sort) {
		System.out.println("before ....");
	}
}
