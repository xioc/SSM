package com.lean.ssm.chapter2.proxy;

import com.lean.ssm.chapter2.reflect.ReflectServiceImpl;

public class ProxyTest {

	public static void main(String[] args) {
//		testJdkProxy();
		tesCGLIBProxy();
	}

	public static void testJdkProxy() {
		JdkProxyExample jdk = new JdkProxyExample();
		// 绑定关系，因为挂在接口HelloWorld下，所以声明代理对象HelloWorld proxy
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		// 注意，此时HelloWorld对象已经是一个代理对象，它会进入代理的逻辑方法invoke里
		proxy.sayHelloWorld();
	}
	
	public static void tesCGLIBProxy() {
	    CglibProxyExample cpe = new CglibProxyExample();
	    ReflectServiceImpl obj = (ReflectServiceImpl)cpe.getProxy(ReflectServiceImpl.class);
	    obj.sayHello("张三");
	}

}
