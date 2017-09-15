var Role = {
    permissions: [],
    init: function() {
        Role.initTable();
    },
    initTable: function () {
        $('#role-table').bootstrapTable({
            url: "role/table",//这个接口需要处理bootstrap table传递的固定参数
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
                    field: 'role.name',
                    title: '角色名称',
                    width: '10%'
                },
                {
                    field: 'role.description',
                    title: '角色描述',
                    width: '15%'
                },
                {
                    field: 'role.available',
                    title: '状态',
                    width: '5%',
                    formatter: function (value, row, index) {
                        return row.role.available ? "<span class='green'>正常</span>" : "<span class='red'>停用</span>";
                    }
                },
                {
                    field: 'resourceStr',
                    title: '拥有资源',
                    width: '50%'
                },
                {
                    title: '操作',
                    align: 'center',
                    events: Role.operateEvents,
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

        },
        'click .remove': function (e, value, row, index) {
            var role_id = row.role.id;
            $.confirm({
                title: '确认',
                content: '确认删除？',
                buttons: {
                    confirm: {
                        text: "删除",
                        btnClass: "btn-red",
                        action: function () {
                            $.ajax({
                                url: "role/delete?id=" + role_id,
                                type: "post",
                                success: function () {
                                    $('#role-table').bootstrapTable('refresh');
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
        },
        'click .disable': function (e, value, row, index) {
            var role_id = row.role.id;
            $.ajax({
                url: "role/status?id=" + role_id + "&status=" + false,
                type: "post",
                success: function () {
                    $('#role-table').bootstrapTable('refresh');
                }
            });
        },
        'click .enable': function (e, value, row, index) {
            var role_id = row.role.id;
            $.ajax({
                url: "role/status?id=" + role_id + "&status=" + true,
                type: "post",
                success: function () {
                    $('#role-table').bootstrapTable('refresh');
                }
            });
        }
    }
}

$(document).ready(function () {
    Role.init();
});