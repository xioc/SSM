package com.ssm.chapter9.pojo;

public class JuiceMaker {
	private Blender blender = null;// 搅拌机
	private String water;// 水描述
	private String fruit;// 水果
	private String sugar;// 糖分描述

	public Blender getBlender() {
		return blender;
	}

	public void setBlender(Blender blender) {
		this.blender = blender;
	}

	public String getWater() {
		return water;
	}


	public void setWater(String water) {
		this.water = water;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	/**
	 * 果汁生成
	 */
	public String makeJuice() {
		blender = new Blender();
		return blender.mix(water, fruit, sugar);
	}
}
