<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>新闻标题</th>
					<th>操作</th>
				</tr>

				<c:forEach var="news" items="${news_List}">
					<tr>
						<td class="first w4 c">${news.enId}</td>
						<td class="w1 c">${news.enTitle}</td>
						<td class="w1 c"><a href="news.do?
						action=jump2modify&en_id=${news.enId}&en_title=${news.enTitle}
						">修改</a> <a href="news.do?action=delete&newsId=${news.enId}">删除</a></td>
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
