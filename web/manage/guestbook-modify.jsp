<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h2>回复留言</h2>
		<div class="manage">
			<form action="/word.do">
				<table class="form">
					<tr>
						<td class="field">留言ID：</td>
						<td><input type="text" class="text" name="ewId" value="${ew_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">留言姓名：</td>
						<td><input type="text" class="text" name="name" value="${ew_name}" /></td>
					</tr>
					<tr>
						<td class="field">留言内容：</td>
						<td>${ew_content}</td>
					</tr>
					<tr>
						<td class="field">回复内容：</td>
						<td><textarea name="replyContent">${ew_reply}</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
