<%@ page language="java"  import="com.ssm.chapter15.pojo.Role"  
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <%
            Role role = (Role) session.getAttribute("role");
            out.println("id = " + role.getId() + "<p/>");
            out.println("roleName = " + role.getRoleName() + "<p/>");
            out.println("note = " + role.getNote() + "<p/>");

            Long id = (Long) session.getAttribute("id");
            out.println("id = " + id + "<p/>");
        %>
    </body>
</html>