package com.ssm.chapter13.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.chapter13.pojo.Role;
import com.ssm.chapter13.service.RoleListService;
import com.ssm.chapter13.service.RoleService;
@Service
public class RoleListServiceImpl implements RoleListService {
	@Autowired
	private RoleService roleService = null;
	Logger log = Logger.getLogger(RoleListServiceImpl.class);
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for (Role role : roleList) {
			try {
				count += roleService.insertRole(role);
			} catch (Exception ex) {
				log.info(ex);
			}
		}
		return count;
	}
}