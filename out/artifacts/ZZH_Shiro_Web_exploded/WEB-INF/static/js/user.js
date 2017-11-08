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
    // 编辑弹层
    modifyDialog: function (e, value, row, index) {
        console.log(row);
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
        html_ += '<input id="org" name="org" type="text" data-provide="typeahead" value="' + row.organizationName + '">';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>拥有角色：</label>';
        html_ += '<input id="role" name="role" type="text" data-provide="typeahead" value="' + row.roleNames + '" />';
        html_ += '</div>';
        html_ += '</div>';
        // 弹层
        $.confirm({
            title: '修改角色',
            content: html_,
            buttons: {
                confirm: {
                    text: "删除",
                    btnClass: "btn-red",
                    action: function () {
                    }
                },
                cancel: {
                    text: "取消",
                    action: function () {}
                }
            },
            onOpen: function() {
                // 所属组织autocomplete
                $("#modify_dialog #org").typeahead({
                    items: 99,
                    minLength: 0,
                    showHintOnFocus: true,
                    source: function (query, process) {
                        return $.ajax({
                            url: 'organization/all',
                            type: 'post',
                            contentType: "application/json",
                            data: query,
                            success: function (results) {
                                var _result = results.map(function (result) {
                                    return result.name;
                                });
                                return process(_result);
                            }
                        });
                    }
                });
                // 拥有角色自动补全
                $("#modify_dialog #role").typeahead({
                    items: 99,
                    minLength: 0,
                    showHintOnFocus: true,
                    source: function (query, process) {
                        return $.ajax({
                            url: 'role/all',
                            type: 'post',
                            contentType: "application/json",
                            data: query,
                            success: function (results) {
                                console.log(results);
                                var _result = results.map(function (result) {
                                    return result.name;
                                });
                                return process(_result);
                            }
                        });
                    }
                });
            }
        });
    }
}

$(document).ready(function () {
    UserController.init();
});