package com.ssm.chapter11.game;

public interface Interceptor {
	
	public void before(Object obj);

    public void after(Object obj);

    public void afterReturning(Object obj);

    public void afterThrowing(Object obj);
}
