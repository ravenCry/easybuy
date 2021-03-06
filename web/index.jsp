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
<div id="main" class="wrap">
	<jsp:include page="include/left.jsp"></jsp:include>
	<div class="main">
		<div class="price-off">
			<h2>今日特价</h2>
			<ul class="product clearfix">
				<c:forEach var="product" items="${productDiscountList}">
					<li>
						<dl>
							<dt><a href="product.do?id=${product.id}&action=detail" target="_blank"></a><img src="${product.filename}" /></dt>
							<dd class="title"><a href="product.do?id=${product.id}&action=detail" target="_blank">${product.name}</a></dd>
							<dd class="price">${product.price}</dd>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="side">
			<jsp:include page="include/news-comment.jsp"></jsp:include>
		</div>
		<div class="spacer clear"></div>
		<div class="hot">
			<h2>热卖推荐</h2>
			<ul class="product clearfix">
				<c:forEach var="product" items="${productHotList}">
					<li>
						<dl>
							<dt><a href="product.do?id=${product.id}&action=detail" target="_blank"></a><img src="${product.filename}" /></dt>
							<dd class="title"><a href="product.do?id=${product.id}&action=detail" target="_blank">${product.name}</a></dd>
							<dd class="price">${product.price}</dd>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
