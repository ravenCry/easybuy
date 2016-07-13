<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<h2>商品管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>商品名称</th>
					<th>操作</th>
				</tr>

					<c:forEach var="product" items="${productList}">
                       <tr>
						<td class="first w4 c">${product.id}</td>
						<td class="w1 c">${product.name}</td>
						   <td class="w1 c"><a href="product.do?
						action=jump2modify&ep_id=${product.id}&ep_name=${product.name}
						">修改</a> <a href="product.do?action=delete&productId=${product.id}">删除</a></td>

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
