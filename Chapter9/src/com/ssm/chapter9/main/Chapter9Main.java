package com.ssm.chapter9.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.chapter9.pojo.JuiceMaker;
import com.ssm.chapter9.pojo.JuiceMaker2;

public class Chapter9Main {
	public static void main(String[] args) {
		testIoC();
	}
	
	public static void testCommon() {
		JuiceMaker juiceMaker = new JuiceMaker();
		juiceMaker.setWater("矿泉水");
		juiceMaker.setFruit("橙子");
		juiceMaker.setSugar("少糖");
		System.out.println(juiceMaker.makeJuice());
	}
	
	public static void testIoC() {
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("spring-cfg.xml");
		JuiceMaker2 juiceMaker2 = (JuiceMaker2) ctx.getBean("juiceMaker2");
		System.out.println(juiceMaker2.makeJuice());
		ctx.close();
	}
}
