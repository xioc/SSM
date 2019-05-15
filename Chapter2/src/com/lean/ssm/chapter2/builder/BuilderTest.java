package com.lean.ssm.chapter2.builder;

public class BuilderTest {
	public static void main(String[] args) {
		TicketHelper helper = new TicketHelper();
		helper.buildAdult("成人票");
		helper.buildChildrenForSeat("有座儿童");
		helper.buildchildrenNoSeat("无票儿童");
		helper.buildElderly("老人票");
		helper.buildSoldier("军人票");
		Object ticket = TicketBuilder.builder(helper);
	}
}
