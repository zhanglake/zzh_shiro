// 务必先引入notify.min.js
document.write("<script language=javascript src='static/plugin/notify.min.js'></script>");

$(document).ajaxError(function () {
    $.bootstrapGrowl("操作异常，请联系管理员", {type: "danger"});
});