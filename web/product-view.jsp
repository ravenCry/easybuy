<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/top.jsp"></jsp:include>
<div id="position" class="wrap">
	您现在的位置：<a href="news.do">易买网</a> &gt; <a href="product-list.jsp">图书音像</a> &gt; 图书
</div>
<div id="main" class="wrap">
	<jsp:include page="include/left.jsp"></jsp:include>
	<div id="product" class="main">
		<h1>${ebProduct.name}</h1>
		<div class="infos">
			<div class="thumb"><img src="${ebProduct.filename}" /></div>
			<div class="buy">
				<p>商城价：<span class="price">${ebProduct.price}</span></p>
				<p>库　存：${ebProduct.stock}</p>
				<p>点击量：${ebProduct.view}</p>
				<div class="button"><input type="button" name="button" value="" onclick="goBuy(${ebProduct.id})" /><a href="shopping.do?action=insert&id=${ebProduct.id}">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				${ebProduct.description}<br />
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
