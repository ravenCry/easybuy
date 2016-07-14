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
		<h2>分类管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>分类名称</th>
					<th>操作</th>
				</tr>
				<c:forEach var="proCategory" items="${proCategory_List}">
					<c:if test="${proCategory.parentId==-1}">
						<tr>
						    <td class="first w4 c">${proCategory.id}</td>
						      <td>${proCategory.name}</td>
						    <td class="w1 c"><a href="/proCategory.do?action=jump2modify&epc_id=${proCategory.id}&epc_name=${proCategory.name}
						    ">修改</a> <a href="proCategory.do?action=delete&proId=${proCategory.id}">删除</a></td>
						</tr>
						<c:forEach var="proCategoryChild" items="${proCategory_List}">
							<c:if test="${proCategoryChild.parentId==proCategory.id}">
								<tr>
									<td class="first w4 c">${proCategoryChild.id}</td>
									<td class="childClass">${proCategoryChild.name}</td>
									<td class="w1 c"><a href="/proCategory.do?action=jump2modify&epc_id=${proCategoryChild.id}&epc_name=${proCategoryChild.name}
						    ">修改</a> <a href="proCategory.do?action=delete&proId=${proCategoryChild.id}">删除</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
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
