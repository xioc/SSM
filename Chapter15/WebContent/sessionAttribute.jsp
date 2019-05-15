<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>session</title>
    </head>
    <body>
        <%
            //设置Session属性
            session.setAttribute("id", 1L);
            //执行跳转
            response.sendRedirect("./attribute/sessionAttribute.do");
        %>
    </body>
</html>