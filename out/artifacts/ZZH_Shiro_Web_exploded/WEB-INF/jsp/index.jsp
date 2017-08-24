<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>zzh_shiro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout-default-latest.css">
</head>
<body>
您好<shiro:principal/>，<a href="${pageContext.request.contextPath}/logout">退出</a>

<br />
<c:forEach items="${menus}" var="m">
    <a href="${pageContext.request.contextPath}/${m.url}" target="content">${m.name}</a><br/>
</c:forEach>
</body>
</html>