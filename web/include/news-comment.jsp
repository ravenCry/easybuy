<%--
  Created by IntelliJ IDEA.
  User: HellCrow
  Date: 2016/7/14
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="news-list">
        <h4>最新公告</h4>
        <ul>
            <c:forEach var="comment" items="${commentList}">
                <li><a href="comment.do?id=${comment.id}&action=detail" target="_blank">${comment.username}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="spacer"></div>
    <div class="news-list">
        <h4>新闻动态</h4>
        <ul>
            <c:forEach var="news" items="${newsList}">
                <li><a href="news.do?id=${news.enId}&action=detail" target="_blank">${news.enTitle}</a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
