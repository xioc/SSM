package com.ssm.chapter5.pojo2;

import java.util.List;

import com.ssm.chapter5.enumeration.SexEnum;

public class User2 {
	private Long id;
	private String userName;
	private String realName;
	private SexEnum sex;
	private String moble;
	private String email;
	private String note;
	// 对角色一对多关联
	private List<Role2> roleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String getMoble() {
		return moble;
	}

	public void setMoble(String moble) {
		this.moble = moble;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Role2> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role2> roleList) {
		this.roleList = roleList;
	}

}
