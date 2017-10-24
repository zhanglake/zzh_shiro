<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>角色管理</title>
    <link rel="stylesheet" href="../static/plugin/JQuery zTree v3.5.15/css/zTreeStyle/zTreeStyle.css">
    <style>
        ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: 220px;
            height: 200px;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <form>
        <input id="id" type="text" style="display: none" value="${role.id}"/>
        <input id="available" type="text" style="display: none"/>
        <div class="form-group">
            <label for="name">角色名:</label>
            <input type="text" name="name" id="name" placeholder="请填写角色名" value="${role.name}"/>
        </div>
        <div class="form-group">
            <label for="description">角色描述:</label>
            <input type="text" name="description" id="description" placeholder="请填写角色描述" value="${role.description}"/>
        </div>
        <div class="form-group">
            <label for="resourceName">拥有的资源列表:</label>
            <input type="text" id="resourceIds" style="display: none;"/>
            <input type="text" id="resourceName" name="resourceName" value="" readonly>
            <a id="menuBtn" href="#">选择</a>
        </div>
    </form>
    <button id="save">${op}</button>

    <%--<form:form method="post" commandName="role">--%>
        <%--<form:hidden path="id"/>--%>
        <%--<form:hidden path="available"/>--%>
        <%--<div class="form-group">--%>
            <%--<form:label path="name">角色名：</form:label>--%>
            <%--<form:input path="name"/>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<form:label path="description">角色描述：</form:label>--%>
            <%--<form:input path="description"/>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<form:label path="resourceIds">拥有的资源列表：</form:label>--%>
            <%--<form:hidden path="resourceIds"/>--%>
            <%--&lt;%&ndash;<input type="text" id="resourceName" name="resourceName" value="${zhangfn:resourceNames(role.resourceIds)}" readonly>&ndash;%&gt;--%>
            <%--<input type="text" id="resourceName" name="resourceName" value="" readonly>--%>
            <%--<a id="menuBtn" href="#">选择</a>--%>
        <%--</div>--%>
        <%--<form:button>${op}</form:button>--%>
    <%--</form:form>--%>

    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>
</body>
<script type="text/javascript" src="../static/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../static/plugin/JQuery zTree v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    function zNodesFunction () {
        return [
            <c:forEach items="${resourceList}" var="r">
            <c:if test="${not r.rootNode}">
            { id:${r.id}, pId:${r.parentId}, name:"${r.name}", checked:false},
            </c:if>
            </c:forEach>
        ]
    }
</script>
<script type="text/javascript" src="../static/js/role-edit.js"></script>
</html>
