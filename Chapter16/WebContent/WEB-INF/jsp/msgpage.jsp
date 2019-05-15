<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Spring MVC国际化</title>
</head>
<body>
	<h2>
	    <!-- 找到属性文件变量名为welcome的配置 -->
		<spring:message code="welcome" />
	</h2>
	Locale: ${pageContext.response.locale }
</body>
</html>