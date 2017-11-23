<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>组织机构管理</title>
    <link rel="stylesheet" href="static/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/plugin/bootstrap-table/src/bootstrap-table.css">
    <link rel="stylesheet" href="static/plugin/jquery-confirm/jquery-confirm.min.css">
    <link rel="stylesheet" href="static/css/default.css">
    <link rel="stylesheet" href="static/plugin/select2/select2.css">
</head>
<body>
<shiro:hasPermission name="organization:create">
    <a href="javascript:void(0);" id="create_organization">新增组织</a><br/>
</shiro:hasPermission>
<table id="organization_table"></table>
</body>
<script type="text/javascript" src="static/jquery-1.11.1.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-table/src/bootstrap-table.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="static/plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/plugin/notify.min.js"></script>
<script type="text/javascript" src="static/plugin/ajaxError.js"></script>
<script type="text/javascript" src="static/plugin/typeahead.js"></script>
<script type="text/javascript" src="static/plugin/select2/select2.min.js"></script>
<script type="text/javascript" src="static/plugin/select2/zh-CN.js"></script>
<script>
    function operateFormatter (row) {
        var html_ = '<shiro:hasPermission name="organization:update">';
        html_ += '<a class="update" href="javascript:void(0);" title="编辑">';
        html_ += '<i class="glyphicon glyphicon-pencil"></i>编辑';
        html_ += '</a></shiro:hasPermission>';
        html_ += '<shiro:hasPermission name="organization:delete">';
        html_ += '<a class="remove" href="javascript:void(0)" title="删除">';
        html_ += '<i class="glyphicon glyphicon-trash"></i>删除';
        html_ += '</a></shiro:hasPermission>';
        return html_;
    }
</script>
<script type="text/javascript" src="static/js/organization.js"></script>
</html>