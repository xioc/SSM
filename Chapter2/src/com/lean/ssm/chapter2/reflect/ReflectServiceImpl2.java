package com.lean.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectServiceImpl2 {
	private String name;

	public ReflectServiceImpl2(String name) {
		this.name = name;
	}

	public void sayHello() {
		System.err.println("hello " + name);
	}
	
	public ReflectServiceImpl2 getInstance() {
	    ReflectServiceImpl2 object = null;
	    try {
	        object = 
	            (ReflectServiceImpl2) 
	            Class.forName("com.lean.ssm.chapter2.reflect.ReflectServiceImpl2").
	            getConstructor(String.class).newInstance("ÕÅÈý");
	    } catch (ClassNotFoundException | InstantiationException 
	                | IllegalAccessException | NoSuchMethodException 
	                | SecurityException | IllegalArgumentException 
	                | InvocationTargetException ex) {
	            ex.printStackTrace();
	    }
	    return object;
	}
}