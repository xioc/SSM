<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>参数</title>
        <!-- 加载Query文件-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.js">
        </script>
        <script type="text/javascript">
        $(document).ready(function () {
            $("#commit").click(function() {
            	var str = $("form").serialize();
            	//提交表单
            	$.post({
                    url: "./params/commonParamPojo2.do",
                    //将form数据序列化，传递给后台，则将数据以roleName=xxx&&note=xxx传递
                    data: $("form").serialize(),
                    //成功后的方法
                    success: function (result) {
                    }
                });
            });
        });
        </script>
    </head>
    <body>
        <form id="form">
            <table>
                <tr>
                    <td>角色名称</td>
                    <td><input id="roleName" name="roleName" value="" /></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><input id="note" name="note" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right"><input id="commit" type="button" value="提交" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>