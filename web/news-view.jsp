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
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 阅读新闻
</div>
<div id="main" class="wrap">
	<div class="left-side">
		<div class="news-list">
			<h4>最新公告</h4>
			<ul>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
			</ul>
		</div>
		<div class="spacer"></div>
		<div class="news-list">
			<h4>新闻动态</h4>
			<ul>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
				<li><a href="news-view.jsp" target="_blank">抢钱啦</a></li>
			</ul>
		</div>
	</div>
	<div id="news" class="right-main">
		<h1>${news.enTitle}</h1>
		<div class="content">
			${news.enContent}
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
