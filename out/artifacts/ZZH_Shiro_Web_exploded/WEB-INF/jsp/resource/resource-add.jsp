<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>
    <form method="post" commandName="resource">
        <input id="id" name="id" type="text" style="display: none" value="${parent.id}"/>
        <div class="form-group">
            <label>父节点名称：</label>
            ${parent.name}
        </div>
        <div class="form-group">
            <label>子节点名称：</label>
            <input id="name" name="name" type="text" />
        </div>
        <div class="form-group">
            <label>类型：</label>
            <select id="type" name="type">
                <option value="menu" selected="selected">菜单</option>
                <option value="button">按钮</option>
            </select>
        </div>
        <div class="form-group">
            <label>URL路径：</label>
            <input id="url" name="url" type="text"/>
        </div>
        <div class="form-group">
            <label>权限字符串：</label>
            <input id="permission" name="permission" type="text"/>
        </div>
    </form>
    <button id="addChild">新增子节点</button>
</body>
</html>