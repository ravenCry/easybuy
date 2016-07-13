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
<div id="menu-mng" class="lefter">
    <div class="box">
        <dl>
            <dt>用户管理</dt>
            <dd><em><a href="/manage/user-add.jsp">新增</a></em><a href="user.do?action=userList">用户管理</a></dd>
            <dt>商品信息</dt>
            <dd><em><a href="/manage/productClass-add.jsp">新增</a></em><a href="proCategory.do?action=productClass_List">分类管理</a></dd>
            <dd><em><a href="/manage/product-add.jsp">新增</a></em><a href="product.do?action=manageList">商品管理</a></dd>
            <dt>订单管理</dt>
            <dd><a href="order.do?action=manageList">订单管理</a></dd>
            <dt>留言管理</dt>
            <dd><a href="/manage/guestbook.jsp">留言管理</a></dd>
            <dt>新闻管理</dt>
            <dd><em><a href="/manage/news-add.jsp">新增</a></em><a href="news.do?action=newsList">新闻管理</a></dd>
        </dl>
    </div>
</div>
</body>
</html>
