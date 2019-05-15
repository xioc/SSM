package com.ssm.chapter5.mapper;

import com.ssm.chapter5.param.PdCountRoleParams;
import com.ssm.chapter5.param.PdFindRoleParams;

public interface PdRoleMapper {

	public void countRole(PdCountRoleParams pdCountRoleParams);
	
	public void findRole(PdFindRoleParams pdFindRoleParams);
}
