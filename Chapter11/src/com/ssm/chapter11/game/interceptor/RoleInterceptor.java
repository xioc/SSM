package com.ssm.chapter11.game.interceptor;

import com.ssm.chapter11.game.Interceptor;

public class RoleInterceptor implements Interceptor {
	@Override
    public void before(Object obj) {
        System.out.println(
        	"准备打印角色信息");
    }

    @Override
    public void after(Object obj) {
        System.out.println(
        	"已经完成角色信息的打印处理");
    }

    @Override
    public void afterReturning(Object obj) {
         System.out.println(
             "刚刚完成打印功能，一切正常。");
    }

    @Override
    public void afterThrowing(Object obj) {
        System.out.println(
            "打印功能执行异常了，查看一下角色对象为空了吗？");
    }
}
