package com.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("role")
public class Role {
	// 赋值long型
	@Value("#{1}")
	private Long id;
	// 字符串赋值
	@Value("#{'role_name_1'}")
	private String roleName;
	// 字符串赋值
	@Value("#{'note_1'}")
	private String note;
	
	public Role() {
		
	}
	
	public Role(Long id, String roleName, String note) {
		this.id = id;
		this.roleName = roleName;
		this.note = note;
	}

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
	
	
}
