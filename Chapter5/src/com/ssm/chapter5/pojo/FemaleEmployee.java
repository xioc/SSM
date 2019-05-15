package com.ssm.chapter5.pojo;

public class FemaleEmployee extends Employee {

	private FemaleHealthForm femaleHealthForm = null;

	public FemaleHealthForm getFemaleHealthForm() {
		return femaleHealthForm;
	}

	public void setFemaleHealthForm(FemaleHealthForm femaleHealthForm) {
		this.femaleHealthForm = femaleHealthForm;
	}
	
	
}
