package com.ssm.chapter16.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//新增Spring MVC的异常映射，code代表异常映射码，而reason则代表异常原因
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "找不到角色信息异常！！")
public class RoleException extends RuntimeException  {
	
	private static final long serialVersionUID = 5040949196309781680L;
}