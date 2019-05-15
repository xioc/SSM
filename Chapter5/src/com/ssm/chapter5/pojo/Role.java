package com.ssm.chapter5.pojo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("role")
public class Role implements Serializable {

	private static final long serialVersionUID = 598736524547906734L;
	
	private Long id;
	private String roleName;
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
