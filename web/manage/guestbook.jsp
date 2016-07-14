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
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach var="ebWord" items="${ebWordList}">
					<tr>
						<td class="first w4 c">${ebWord.ew_id}</td>
						<td class="w1 c">${ebWord.ew_name}</td>
						<td>${ebWord.ew_content}</td>
						<c:if test="${ebWord.ew_reply==null}">
							<td class="w1 c">未回复</td>
						</c:if>
						<c:if test="${ebWord.ew_reply!=null}">
							<td class="w1 c">已回复</td>
						</c:if>
						<td class="w1 c">
							<a href="/word.do?action=jump2Modify&ew_id=${ebWord.ew_id}&ew_name=${ebWord.ew_name}&ew_content=${ebWord.ew_content}&ew_reply=${ebWord.ew_reply}">回复</a>
							<a href="/word.do?action=delete&ew_id=${ebWord.ew_id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="#">上一页</a></li>
					<li class="current">1</li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">下一页</a></li>
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
