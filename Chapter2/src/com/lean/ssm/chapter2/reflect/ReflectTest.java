package com.lean.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;
		try {
			object = (ReflectServiceImpl) Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl")
					.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return object;
	}

	public Object reflectMethod() {
		Object returnObj = null;
		ReflectServiceImpl target = new ReflectServiceImpl();
		try {
			Method method = ReflectServiceImpl.class.getMethod("sayHello", String.class);
			returnObj = method.invoke(target, "张三");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ex) {
			ex.printStackTrace();
		}
		return returnObj;
	}

	public Object reflect() {
		ReflectServiceImpl object = null;
		try {
			object = (ReflectServiceImpl) Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl")
					.newInstance();
			Method method = object.getClass().getMethod("sayHello", String.class);
			method.invoke(object, "张三");
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
			ex.printStackTrace();
		}
		return object;
	}

}
