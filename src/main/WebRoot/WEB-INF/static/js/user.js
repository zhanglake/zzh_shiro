var UserController = {
    init: function () {
        UserController.initTable();
    },
    initTable: function () {
        $('#user_table').bootstrapTable({
            url: "user/list", //这个接口需要处理bootstrap table传递的固定参数
            method: "post",
            dataType: "json",
            pagination: true, //分页
            sidePagination: "server", //服务端处理分页
            search: true,
            queryParamsType: "",
            responseHandler: function (res) {
                return {
                    "rows": res.results, // 具体每一个bean的列表
                    "total": res.totalRecord // 总共有多少条返回数据
                }
            },
            columns: [
                {
                    field: 'userName',
                    title: '用户名称',
                    width: '10%'
                },
                {
                    field: 'organizationName',
                    title: '所属组织',
                    width: '15%'
                },
                {
                    field: 'roleNames',
                    title: '拥有角色',
                    width: '15%'
                },
                {
                    field: 'roleNames',
                    title: '状态',
                    width: '15%',
                    formatter: function (value, row, index) {
                        return row.locked ? "<span class='red'>锁定</span>" : "<span class='green'>正常</span>";
                    }
                },
                {
                    title: '操作',
                    align: 'center',
                    events: UserController.operateEvents,
                    formatter: function (value, row, index) {
                        return operateFormatter(row);
                    }
                }
            ]
        });
        $("#create_user").click(function () {
            UserController.createDialog();
        });
    },
    // 行操作
    operateEvents: {
        'click .update': function (e, value, row, index) {
            UserController.modifyDialog(e, value, row, index);
        },
        'click .remove': function (e, value, row, index) {
            var user_id = row.id;
            $.confirm({
                title: '确认',
                content: '确认删除？',
                buttons: {
                    confirm: {
                        text: "删除",
                        btnClass: "btn-red",
                        action: function () {
                            $.get("user/" + user_id + "/delete", function(data) {
                                $('#user_table').bootstrapTable('refresh');
                                $.bootstrapGrowl(data.message, {type: "success"});
                            });
                        }
                    },
                    cancel: {
                        text: "取消",
                        action: function () {}
                    }
                }
            });
        },
        'click .disable': function (e, value, row, index) {
            var user_id = row.id;
            $.get("user/" + user_id + "/" + false + "/status", function (data) {
                $('#user_table').bootstrapTable('refresh');
                $.bootstrapGrowl(data.message, {type: "success"});
            });
        },
        'click .enable': function (e, value, row, index) {
            var user_id = row.id;
            $.get("user/" + user_id + "/" + true + "/status", function (data) {
                $('#user_table').bootstrapTable('refresh');
                $.bootstrapGrowl(data.message, {type: "success"});
            });
        }
    },
    // 编辑用户弹层
    modifyDialog: function (e, value, row, index) {
        var html_ = '<div id="modify_dialog">';
        html_ += '<div class="form-group">';
        html_ += '<input id="id" name="id" type="text" value="' + row.id + '" style="display: none;" />';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>用户名称：</label>';
        html_ += '<input id="username" name="username" type="text" value="' + row.userName + '" />';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>所属组织：</label>';
        //html_ += '<input id="org" name="org" type="text" data-provide="typeahead" value="' + row.organizationName + '">';
        html_ += '<select id="org" style="width: 200px;"></select>';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>拥有角色：</label>';
        //html_ += '<input id="role" name="role" type="text" data-provide="typeahead" value="' + row.roleNames + '" />';
        html_ += '<select id="role" style="width: 200px;"></select>';
        html_ += '</div>';
        html_ += '</div>';
        // 弹层
        $.confirm({
            title: '修改角色',
            content: html_,
            buttons: {
                confirm: {
                    text: "确定",
                    btnClass: "btn-red",
                    action: function () {
                        var userId = $("#modify_dialog #id").val() ? $("#modify_dialog #id").val() : null;
                        var userName = $("#modify_dialog #username").val() ? $("#modify_dialog #username").val() : null;
                        var selectedOrgId = $("#modify_dialog #org").val();
                        var selectedRoleIds = $("#modify_dialog #role").val();
                        var param = {
                            id: userId,
                            userName: userName,
                            organizationId: selectedOrgId,
                            roleIds: selectedRoleIds
                        }
                        if (userId && userName) {
                            $.ajax({
                                url: "user/update",
                                type: "post",
                                data: JSON.stringify(param),
                                contentType: "application/json",
                                success: function (data) {
                                    $('#user_table').bootstrapTable('refresh');
                                    $.bootstrapGrowl(data.message, {type: data.status.toLowerCase()});
                                    if (data.status == "warning") {
                                        return false;
                                    }
                                }
                            })
                        } else {
                            $.bootstrapGrowl("请填写必填项目", {type: "warning"});
                            return false;
                        }
                    }
                },
                cancel: {
                    text: "取消",
                    action: function () {}
                }
            },
            onOpen: function() {
                // 选择组织
                $("#modify_dialog #org").select2({
                    placeholder:'请选择',
                    language: "zh-CN",
                    allowClear: true,
                    ajax: {
                        url: "organization/all",
                        type: 'post',
                        contentType: "application/json",
                        data: function(params) {
                            return params.term;
                        },
                        processResults: function(data) {
                            return {
                                results: data
                            }
                        }
                    }
                });
                // 选择角色
                $("#modify_dialog #role").select2({
                    placeholder:'请选择',
                    language: "zh-CN",
                    multiple: true,
                    allowClear: true,
                    ajax: {
                        url: "role/all",
                        type: 'post',
                        contentType: "application/json",
                        data: function(params) {
                            return params.term;
                        },
                        processResults: function(data) {
                            return {
                                results: data
                            }
                        }
                    }
                });
            }
        });
    },
    // 新增用户弹层
    createDialog: function () {
        var html_ = '<div id="create_dialog">';
        html_ += '<div class="form-group">';
        html_ += '<input id="id" name="id" type="text" style="display: none;" />';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>用户名称：</label>';
        html_ += '<input id="username" name="username" type="text" />';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>所属组织：</label>';
        html_ += '<select id="org" style="width: 200px;"></select>';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>拥有角色：</label>';
        html_ += '<select id="role" style="width: 200px;"></select>';
        html_ += '</div>';
        html_ += '</div>';
        // 弹层
        $.confirm({
            title: '新增角色',
            content: html_,
            buttons: {
                confirm: {
                    text: "确定",
                    btnClass: "btn-red",
                    action: function () {
                        var userName = $("#create_dialog #username").val() ? $("#create_dialog #username").val() : null;
                        var selectedOrgId = $("#create_dialog #org").val();
                        var selectedRoleIds = $("#create_dialog #role").val();
                        var param = {
                            userName: userName,
                            organizationId: selectedOrgId,
                            roleIds: selectedRoleIds
                        }
                        if (userName) {
                            $.ajax({
                                url: "user/create",
                                type: "post",
                                data: JSON.stringify(param),
                                contentType: "application/json",
                                success: function (data) {
                                    $('#user_table').bootstrapTable('refresh');
                                    $.bootstrapGrowl(data.message, {type: data.status.toLowerCase()});
                                    if (data.status == "warning") {
                                        return false;
                                    }
                                }
                            })
                        } else {
                            $.bootstrapGrowl("请填写必填项目", {type: "warning"});
                            return false;
                        }
                    }
                },
                cancel: {
                    text: "取消",
                    action: function () {}
                }
            },
            onOpen: function() {
                // 选择组织
                $("#create_dialog #org").select2({
                    placeholder:'请选择',
                    language: "zh-CN",
                    allowClear: true,
                    ajax: {
                        url: "organization/all",
                        type: 'post',
                        contentType: "application/json",
                        data: function(params) {
                            return params.term;
                        },
                        processResults: function(data) {
                            return {
                                results: data
                            }
                        }
                    }
                });
                // 选择角色
                $("#create_dialog #role").select2({
                    placeholder:'请选择',
                    language: "zh-CN",
                    multiple: true,
                    allowClear: true,
                    ajax: {
                        url: "role/all",
                        type: 'post',
                        contentType: "application/json",
                        data: function(params) {
                            return params.term;
                        },
                        processResults: function(data) {
                            return {
                                results: data
                            }
                        }
                    }
                });
            }
        });
    }
}

$(document).ready(function () {
    UserController.init();
});