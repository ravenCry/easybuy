<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="/manage/include/top.jsp"></jsp:include>
<div id="main" class="wrap">
	<jsp:include page="/manage/include/left.jsp"></jsp:include>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach var="user" items="${user_List}">
					<tr>
						<td class="first w4 c">${user.id}</td>
						<td class="w1 c">${user.name}</td>
						<td class="w1 c">${user.sex}</td>
						<td class="w1 c">${user.email}</td>
						<td class="w1 c">${user.mobile}</td>


						<td class="w1 c"><a href="user.do?
						action=jump2modify&eb_user_id=${user.id}&
						eb_user_name=${user.name}&eu_password=${user.password}&
						eu_sex=${user.sex}&eu_mobile=${user.mobile}&eb_address=${user.address}">修改</a> <a href="user.do?action=delete&userId=${user.id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
