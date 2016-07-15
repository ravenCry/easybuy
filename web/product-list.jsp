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
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; <a href="product-list.jsp">图书音像</a> &gt; 图书
</div>
<div id="main" class="wrap">
	<jsp:include page="include/left.jsp"></jsp:include>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="/product.do?action=epc_child_id&pageIndex=1&pc_id=${pc_id}">首页</a></li>
					<c:if test="${pageIndex>1}"><li><a href="/product.do?action=epc_child_id&pageIndex=${pageIndex-1}&pc_id=${pc_id}">上一页</a></li></c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<li><a href="/product.do?action=epc_child_id&pageIndex=${page}&pc_id=${pc_id}">${page}</a></li>
					</c:forEach>
					<c:if test="${pageIndex<totalPage}"><li><a href="/product.do?action=epc_child_id&pageIndex=${pageIndex+1}&pc_id=${pc_id}">下一页</a></li></c:if>
					<li><a href="/product.do?action=epc_child_id&pageIndex=${totalPage}&pc_id=${pc_id}">末页</a></li>
				</ul>
			</div>
			<div class="clear"></div>
			<ul class="product clearfix">
			<c:forEach var="product" items="${productKindList}">
				<li>
					<dl>
						<dt><a href="product.do?id=${product.id}&action=detail" target="_blank"></a><img src="${product.filename}" /></dt>
						<dd class="title"><a href="product.do?id=${product.id}&action=detail" target="_blank">${product.name}</a></dd>
						<dd class="price">${product.price}</dd>
					</dl>
				</li>
			</c:forEach>
				</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="/product.do?action=epc_child_id&pageIndex=1&pc_id=${pc_id}">首页</a></li>
					<c:if test="${pageIndex>1}"><li><a href="/product.do?action=epc_child_id&pageIndex=${pageIndex-1}&pc_id=${pc_id}">上一页</a></li></c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<li><a href="/product.do?action=epc_child_id&pageIndex=${page}&pc_id=${pc_id}">${page}</a></li>
					</c:forEach>
					<c:if test="${pageIndex<totalPage}"><li><a href="/product.do?action=epc_child_id&pageIndex=${pageIndex+1}&pc_id=${pc_id}">下一页</a></li></c:if>
					<li><a href="/product.do?action=epc_child_id&pageIndex=${totalPage}&pc_id=${pc_id}">末页</a></li>
				</ul>
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
