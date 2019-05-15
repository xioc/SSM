package com.ssm.chapter9.el.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.ssm.chapter10.el.pojo.ElBean;
import com.ssm.chapter10.el.pojo.Role;
import com.ssm.chapter9.el.config.ElConfig;

public class ElMain {

	public static void main(String[] args) {
		test1();
		test2();
	}
	
	private static void test1() {
		//表达式解析器
		ExpressionParser parser = new SpelExpressionParser();
		//设置表达式
		Expression exp = parser.parseExpression("'hello world'");
		String str = (String) exp.getValue();
		System.out.println(str);
		//通过EL访问普通方法
		exp = parser.parseExpression("'hello world'.charAt(0)");
		char ch = (Character) exp.getValue();
		System.out.println(ch);
		//通过EL访问的getter方法
		exp = parser.parseExpression("'hello world'.bytes");
		byte[] bytes = (byte[]) exp.getValue();
		System.out.println(bytes);
		//通过EL访问属性，相当于"hello world".getBytes().length
		exp = parser.parseExpression("'hello world'.bytes.length");
		int length = (Integer)exp.getValue();
		System.out.println(length);
		exp = parser.parseExpression("new String('abc')");
		String abc = (String)exp.getValue();
		System.out.println(abc);
		//创建角色对象
		Role role = new Role(1L, "role_name", "note");
		exp = parser.parseExpression("note");
		//相当于从role中获取备注信息
		String note = (String) exp.getValue(role);
		System.out.println(note);

		//变量环境类，并且将角色对象role作为其根节点
		EvaluationContext ctx = new StandardEvaluationContext(role);
		//变量环境类操作根节点
		parser.parseExpression("note").setValue(ctx, "new_note");
		//获取备注，这里的String.class指明，我们希望返回的是一个字符串
		note = parser.parseExpression("note").getValue(ctx, String.class);
		System.out.println(note);
		//调用getRoleName方法
		String roleName = parser.parseExpression("getRoleName()").getValue(ctx, String.class);
		System.out.println(roleName);

		//新增环境变量
		List<String> list = new ArrayList<String>();
		list.add("value1");
		list.add("value2");
		//给变量环境增加变量
		ctx.setVariable("list", list);
		//通过表达式去读写环境变量的值
		parser.parseExpression("#list[1]").setValue(ctx, "update_value2");
		System.out.println(parser.parseExpression("#list[1]").getValue(ctx));
	}
	
	public static void test2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
		ElBean elBean = context.getBean(ElBean.class);
		System.out.println(elBean.getId() + "\t" + elBean.getRole().getRoleName() + "\t" + elBean.getNote());
	}
}
