package com.lean.ssm.chapter2.intercept;

import com.lean.ssm.chapter2.proxy.HelloWorld;
import com.lean.ssm.chapter2.proxy.HelloWorldImpl;

public class TestInterceptor {
	public static void main(String[] args) {
		testInterceptor();
		testChain();
	}
	
	
	public static void testInterceptor() {
		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), 
				"com.lean.ssm.chapter2.intercept.MyInterceptor");
		proxy.sayHelloWorld();
	}
	
	public static void testChain() {
		HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(
                new HelloWorldImpl(), "com.lean.ssm.chapter2.intercept.Interceptor1");
        HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(
                proxy1, "com.lean.ssm.chapter2.intercept.Interceptor2");
        HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(
                proxy2, "com.lean.ssm.chapter2.intercept.Interceptor3");
        proxy3.sayHelloWorld();
	}
}
