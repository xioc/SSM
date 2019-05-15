<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数</title>
<!-- 加载Query文件-->
<script src="https://code.jquery.com/jquery-3.2.0.js"></script>
<!--
        此处插入JavaScript脚本
-->
<script type="text/javascript">
    /** 传递JSON**/
    /*
	$(document).ready(function() {
		//JSON参数和类RoleParams一一对应
		var data = {
			//角色查询参数
			roleName : 'role',
			note : 'note',
			//分页参数
			pageParams : {
				start : 0,
				limit : 20
			}
		}
		//Jquery的post请求
		$.post({
			url : "./params/findRoles.do",
			//此处需要告知传递参数类型为JSON，不能缺少
			contentType : "application/json",
			//将JSON转化为字符串传递
			data : JSON.stringify(data),
			//成功后的方法
			success : function(result) {
			}
		});
	});
    */
    
    /**传递数组**/
    /*
    $(document).ready(function () {
        //删除角色数组
        var idList = [1, 2, 3];
        //jQuery的post请求
        $.post({
            url: "./params/deleteRoles.do",
            //将JSON转化为字符串传递
            data: JSON.stringify(idList),
            //指定传递数据类型，不可缺少
            contentType: "application/json",
            //成功后的方法
            success: function (result) {
            }
        });
    });
    */
    
    $(document).ready(function () {
        //新增角色数组
        var roleList = [
            {roleName: 'role_name_1', note: 'note_1'},
            {roleName: 'role_name_2', note: 'note_2'},
            {roleName: 'role_name_3', note: 'note_3'}
        ];
        //jQuery的post请求
        $.post({
            url: "./params/addRoles.do",
            //将JSON转化为字符串传递
            data: JSON.stringify(roleList),
            contentType: "application/json",
            //成功后的方法
            success: function (result) {
            }
        });
    });
    
</script>
</head>
<body>
    <!-- 根据你的需要改变请求url -->
	<form id="form" action="./params/commonParams.do">
		<table>
			<tr>
				<td>角色名称</td>
                <td><input id="roleName" name="roleName" value="" /></td>
				<!--
				<td><input id="role_name" name="role_name" value="" /></td>
				-->
			</tr>
			<tr>
				<td>备注</td>
				<td><input id="note" name="note" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>