package com.ssm.chapter11.multi.bean.impl;

import org.springframework.stereotype.Component;

import com.ssm.chapter11.multi.bean.MultiBean;

@Component
public class MultiBeanImpl implements  MultiBean {

	@Override
	public void testMulti() {
		System.out.println("test multi aspects!!");
	}

}