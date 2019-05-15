package com.ssm.chapter9.pojo;

public class JuiceMaker2  {
	private String beverageShop = null;
	private Source source = null;

	public String getBeverageShop() {
		return beverageShop;
	}

	public void setBeverageShop(String beverageShop) {
		this.beverageShop = beverageShop;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String makeJuice() {
		String juice = "这是一杯由" + beverageShop + "饮品店，提供的" + source.getSize() + source.getSugar() + source.getFruit();
		return juice;
	}

	public void init() {
		System.out.println("【" + this.getClass().getSimpleName() + "】自定义初始化方法");
	}
	
	public void myDestroy() {
		System.out.println("【" + this.getClass().getSimpleName() + "】自定义销毁方法");
	}
}
