<%--
  Created by IntelliJ IDEA.
  User: zcx
  Date: 2016/7/13
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="../images/logo.gif" /></div>
    <div class="help"><a href="../index.jsp">返回前台页面</a></div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="index.jsp">首页</a></li>
            <li><a href="user.jsp">用户</a></li>
            <li><a href="product.jsp">商品</a></li>
            <li><a href="order.jsp">订单</a></li>
            <li><a href="guestbook.jsp">留言</a></li>
            <li><a href="news.jsp">新闻</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="welcome wrap">
        管理员pillys您好，今天是2012-12-21，欢迎回到管理后台。
    </div>
</div>
<div id="position" class="wrap">
    您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>

</body>
</html>
