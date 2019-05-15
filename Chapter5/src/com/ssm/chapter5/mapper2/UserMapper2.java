package com.ssm.chapter5.mapper2;

import java.util.List;

import com.ssm.chapter5.pojo2.User2;

public interface UserMapper2 {
	
    public User2 getUser(Long id);
    
    public List<User2> findUserByRoleId(Long roleId);
}
