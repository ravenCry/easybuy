<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/12
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="lefter">
    <div class="box">
        <h2>商品分类</h2>
        <dl>
            <c:forEach var="proCategory" items="${proCategoryList}">
                <c:if test="${proCategory.parentId==-1}">
                    <dt>${proCategory.name}</dt>
                    <c:forEach var="proCategoryChild" items="${proCategoryList}">
                        <c:if test="${proCategoryChild.parentId==proCategory.id}">
                            <dd><a href="product.do?action=epc_child_id&pc_id=${proCategoryChild.id}">${proCategoryChild.name}</a></dd>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </dl>
    </div>
    <div class="spacer"></div>
    <div class="last-view">
        <h2>最近浏览</h2>
        <dl class="clearfix">
            <c:forEach var="recent" items="${recentList}">
                <dt><img src="${recent.filename}" width="54px" height="54px"/></dt>
                <dd><a href="/product.do?id=${recent.id}&action=detail">${recent.name}</a></dd>
            </c:forEach>
        </dl>
    </div>
</div>
</body>
</html>

