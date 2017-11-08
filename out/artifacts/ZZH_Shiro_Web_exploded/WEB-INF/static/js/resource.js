var Resource = {
    init: function () {
        // treetable控件参数
        var treeTableOption = {
            theme: "vsStyle",
            expandable: true,
            expandNode: 1
        };
        // 初始化treetable
        $("#table").treetable(treeTableOption);
        // 添加节点
        Resource.dialogAdd();
        // 删除节点
        Resource.dialogDelete();
        // 修改节点
        Resource.dialogUpdate();
    },
    // 添加节点
    dialogAdd: function () {
        $(".add-node").click(function () {
            // 获取当前父节点id和name
            var parentId = parseInt($(this).attr("data-id"));
            var parentName = $(this).attr("data-name");
            $("#dialog_add #pname").html(parentName);
            // 添加子节点请求
            var html_ = '<div id="dialog">' + $("#dialog_add").html() + '</div>';
            $.confirm({
                title: '添加子节点',
                content: html_,
                buttons: {
                    confirm: {
                        text: "添加",
                        btnClass: "btn-red",
                        action: function () {
                            var id = $("#dialog #id").val();
                            var name = $("#dialog #name").val();
                            var type = $("#dialog #type").val();
                            var url = $("#dialog #url").val();
                            var permission = $("#dialog #permission").val();
                            var data = {
                                id: id ? id : null,
                                name: name,
                                type: type,
                                url: url,
                                permission: permission,
                                parentId: parentId
                            };
                            $.ajax({
                                url: 'resource/create',
                                type: 'post',
                                contentType: "application/json",
                                data: JSON.stringify(data),
                                success: function (data) {
                                    $.bootstrapGrowl(data.message, {type: "success"});
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消",
                        action: function () {}
                    }
                }
            });
        });
    },
    // 删除节点
    dialogDelete: function () {
        $(".delete-node").click(function() {
            var resourceId = $(this).attr("data-id");
            $.confirm({
                title: '确认删除',
                content: "确认删除吗？",
                buttons: {
                    confirm: {
                        text: "确认",
                        btnClass: "btn-red",
                        action: function () {
                            $.get("resource/" + resourceId + "/delete", function (data) {
                                $.bootstrapGrowl(data.message, {type: "success"});
                            });
                        }
                    }
                }
            });
        });
    },
    // 修改节点
    dialogUpdate: function () {
        $(".update-node").click(function () {
            // 获取当前节点的信息
            var resourceId = $(this).attr("data-id");
            var name = $(this).attr("data-name");
            var type = $(this).attr("data-type");
            var url = $(this).attr("data-url");
            var permission = $(this).attr("data-permission");
            var html_ = '<div id="dialog_update">';
            html_ += '<div class="form-group">';
            html_ += '<input id="id_update" name="id" type="text" value="' + resourceId + '" style="display: none;" />';
            html_ += '</div>'
            html_ += '<div class="form-group">';
            html_ += '<label>节点名称：</label>';
            html_ += '<input id="name_update" name="name" type="text" value="' + name + '" />';
            html_ += '</div>'
            html_ += '<div class="form-group">';
            html_ += '<label>类型：</label>';
            html_ += '<select id="type_update" name="type">';
            if (type == 'menu') {
                html_ += '<option value="menu" selected="selected">菜单</option>';
                html_ += '<option value="button">按钮</option>';
            } else {
                html_ += '<option value="menu">菜单</option>';
                html_ += '<option value="button" selected="selected">按钮</option>';
            }
            html_ += '</select>';
            html_ += '</div>'
            html_ += '<div class="form-group">';
            html_ += '<label>URL路径：</label>';
            html_ += '<input id="url_update" name="url" type="text" value="' + url + '" />';
            html_ += '</div>'
            html_ += '<div class="form-group">';
            html_ += '<label>权限字符串：</label>';
            html_ += '<input id="permission_update" name="permission" type="text" value="' + permission + '" />';
            html_ += '</div>'
            html_ += '</div>'
            $.confirm({
                title: '修改节点',
                content: html_,
                buttons: {
                    confirm: {
                        text: "确定",
                        btnClass: "btn-red",
                        action: function () {
                            var id = $("#dialog_update #id_update").val();
                            var name = $("#dialog_update #name_update").val();
                            var type = $("#dialog_update #type_update").val();
                            var url = $("#dialog_update #url_update").val();
                            var permission = $("#dialog_update #permission_update").val();
                            var data = {
                                id: id ? id : null,
                                name: name,
                                type: type,
                                url: url,
                                permission: permission,
                                parentId: null
                            };
                            $.ajax({
                                url: 'resource/create',
                                type: 'post',
                                contentType: "application/json",
                                data: JSON.stringify(data),
                                success: function (data) {
                                    $.bootstrapGrowl(data.message, {type: "success"});
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消",
                        action: function () {}
                    }
                }
            });
        });
    }
}

$(document).ready(function () {
    Resource.init();
});