<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/login.action" method="post">
	用户名<input type="text"  name="username" value="hashahshh"/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>