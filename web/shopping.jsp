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
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="order.do">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="scItem" items="${myShoppingCarItems}">
				<tr id="product_id_${scItem.esp_id}">
					<td class="thumb"><img src="${scItem.ebProduct.filename}" />
						<a href="product-view.jsp">${scItem.ebProduct.name}</a></td>
					<td class="price" id="price_id_${scItem.esp_id}">
						<span>￥${String.format("%.2f", scItem.ebProduct.price*scItem.esh_quantity)}</span>
						<input type="hidden" value="${scItem.ebProduct.price}" />
					</td>
					<td class="number">
						<dl>
							<dt><input id="number_id_${scItem.esp_id}" type="text" name="number" value="${scItem.esh_quantity}" /></dt>
							<dd onclick="reloadPrice(${scItem.esp_id},true);">+</dd>
							<dd onclick="reloadPrice(${scItem.esp_id},false);">-</dd>
						</dl>
					</td>
					<td class="delete"><a href="javascript:delShopping(${scItem.esp_id});">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>

</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
