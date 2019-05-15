package com.ssm.chapter15.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ssm.chapter15.pojo.Transaction;

public class TransactionValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		//判断验证是否为Transaction，如果是则进行判断[修改为：验证]
		return Transaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Transaction trans = (Transaction) target;
		//求交易金额和价格×数量的差额
		double dis = trans.getAmount() - (trans.getPrice() * trans.getQuantity());
		//如果差额大于0.01，则认为业务错误
		if (Math.abs(dis) > 0.01) {
			//加入错误信息
			errors.rejectValue("amount", null, "交易金额和购买数量与价格不匹配");
		}
	}
}
