<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/top.jsp"></jsp:include>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册易买网</h1>
			<div class="msg">
<c:choose>
	<c:when test="${msg=='成功'}">
		<p>恭喜：注册成功！</p>
		<p>正在进入首页...</p>
		<script type="text/javascript">
			setTimeout(location.href="login.do?userName=${currentUser.name}&passWord=${currentUser.password}", 3000);
		</script>
	</c:when>
	<c:otherwise>
		<p>${msg}！</p>
		<p>返回注册页面...</p>
		<script type="text/javascript">
			setTimeout("location.href='register.jsp'", 3000);
		</script>
	</c:otherwise>
	</c:choose>

			</div>
		</div>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
