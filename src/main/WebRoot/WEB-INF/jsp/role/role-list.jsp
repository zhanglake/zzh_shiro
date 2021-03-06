<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>角色管理</title>
  <link rel="stylesheet" href="static/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="static/plugin/bootstrap-table/src/bootstrap-table.css">
  <link rel="stylesheet" href="static/css/jquery-confirm.min.css">
  <link rel="stylesheet" href="static/css/default.css">
</head>
<body>
  <shiro:hasPermission name="role:create">
    <a href="${pageContext.request.contextPath}/role/create">角色新增</a><br/>
  </shiro:hasPermission>

  <table id="role-table"></table>
</body>
<script type="text/javascript" src="static/jquery-1.11.1.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-table/src/bootstrap-table.js"></script>
<script type="text/javascript" src="static/plugin/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="static/plugin/jquery-confirm.min.js"></script>
<script type="text/javascript">
  function operateFormatter (row) {
    var html_ = '<shiro:hasPermission name="role:update">';
    html_ += '<a class="update" href="${pageContext.request.contextPath}/role/update?id=' + row.role.id + '" title="编辑">';
    html_ += '<i class="glyphicon glyphicon-pencil"></i>编辑';
    html_ += '</a></shiro:hasPermission> ';
    html_ += '<shiro:hasPermission name="role:delete">';
    html_ += '<a class="remove" href="javascript:void(0)" title="删除">';
    html_ += '<i class="glyphicon glyphicon-trash"></i>删除';
    html_ += '</a></shiro:hasPermission> ';
    if (row.role.available) {
      html_ += '<a class="disable" href="javascript:void(0)" title="禁用">';
      html_ += '<i class="glyphicon glyphicon-ban-circle"></i>禁用';
      html_ += '</a>';
    } else {
      html_ += '<a class="enable" href="javascript:void(0)" title="启用">';
      html_ += '<i class="glyphicon glyphicon-ok-circle"></i>启用';
      html_ += '</a>';
    }
    return html_;
  }
</script>
<script type="text/javascript" src="static/js/role.js"></script>
</html>
