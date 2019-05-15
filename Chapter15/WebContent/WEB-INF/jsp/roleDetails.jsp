<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>out标签的使用</title>
</head>
<body>
</body>
<center>
	<table border="1">
		<tr>
			<td>标签</td>
			<td>值</td>
		</tr>
		<tr>
			<td>角色编号</td>
			<td><c:out value="${role.id}"></c:out></td>
		</tr>
		<tr>
			<td>角色名称</td>
			<td><c:out value="${role.roleName}"></c:out></td>
		</tr>
		<tr>
			<td>角色备注</td>
			<td><c:out value="${role.note}"></c:out></td>
		</tr>
	</table>
</center>
</html>