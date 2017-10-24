<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>资源管理</title>
    <link rel="stylesheet" href="static/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/plugin/bootstrap-table/src/bootstrap-table.css">
    <link rel="stylesheet" href="static/css/jquery-confirm.min.css">
    <link rel="stylesheet" href="static/css/default.css">
    <link rel="stylesheet" href="static/plugin/jquery-treetable/stylesheets/jquery.treetable.css">
    <link rel="stylesheet" href="static/plugin/jquery-treetable/stylesheets/jquery.treetable.theme.default.css">
</head>
<body>
<table id="table">
    <thead>
    <tr>
        <th>名称</th>
        <th>类型</th>
        <th>URL路径</th>
        <th>权限字符串</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resourceList}" var="resource">
        <tr data-tt-id='${resource.id}' <c:if test="${not resource.rootNode}">data-tt-parent-id='${resource.parentId}'</c:if>>
            <td>${resource.name}</td>
            <td>${resource.type.info}</td>
            <td>${resource.url}</td>
            <td>${resource.permission}</td>
            <td>
                <shiro:hasPermission name="resource:create">
                    <c:if test="${resource.type ne 'button'}">
                        <a href="${pageContext.request.contextPath}/resource/${resource.id}/appendChild">添加子节点</a>
                    </c:if>
                </shiro:hasPermission>

                <shiro:hasPermission name="resource:update">
                    <a href="${pageContext.request.contextPath}/resource/${resource.id}/update">修改</a>
                </shiro:hasPermission>

                <c:if test="${not resource.rootNode}">
                    <shiro:hasPermission name="resource:delete">
                        <a class="deleteBtn" href="#" data-id="${resource.id}">删除</a>
                    </shiro:hasPermission>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="static/jquery-1.11.1.js"></script>
<script src="static/plugin/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
<script src="static/js/resource.js"></script>

<!-- 新增子节点 -->
<div id="dialog_add" style="display: none;">
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
</div>

</body>
</html>