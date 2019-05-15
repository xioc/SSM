<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>date</title>
    </head>
    <body>
        <form id="form" action="./convert/formatPojo.do">
            <table>
                <tr>
                    <td>日期</td>
                    <td><input id="date " name="date1" type="text"
                               value="2017-06-01" /></td>
                </tr>
                <tr>
                    <td>日期</td>
                    <td><input id="amount " name="amount1" type="text"
                               value="123,000.00" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right"><input id="commit" type="submit" value="提交" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>