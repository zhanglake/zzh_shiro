var OrganizationController = {
    init: function () {
        $("#organization_table").bootstrapTable({
            url: "organization/list", //这个接口需要处理bootstrap table传递的固定参数
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
                    field: 'name',
                    title: '组织机构名称',
                    width: '80%'
                },
                {
                    title: '操作',
                    align: 'center',
                    events: OrganizationController.operateEvents,
                    formatter: function (value, row, index) {
                        return operateFormatter(row);
                    }
                }
            ]
        });
    },
    operateEvents:  {
        'click .update': function(e, value, row, index) {
            OrganizationController.updateDialog(e, value, row, index);
        },
        'click .delete': function(e, value, row, index) {}
    },
    // 修改组织机构弹层
    updateDialog: function (e, value, row, index) {
        var html_ = '<div id="modify_dialog">';
        html_ += '<div class="form-group">';
        html_ += '<input id="id" name="id" type="text" value="' + row.id + '" style="display: none;" />';
        html_ += '</div>';
        html_ += '<div class="form-group">';
        html_ += '<label>组织机构名称：</label>';
        html_ += '<input id="orgname" name="orgname" type="text" value="' + row.userName + '" />';
        html_ += '</div>';
        $.confirm({
            title: '修改角色',
            content: html_,
            buttons: {
                confirm: {
                    text: "确定",
                    btnClass: "btn-red",
                    action: function () {
                        var orgId = $("#modify_dialog #id").val() ? $("#modify_dialog #id").val() : null;
                        var orgName = $("#modify_dialog #orgname").val() ? $("#modify_dialog #orgname").val() : null;
                        var param = {
                            organizationId: orgId,
                            organizationName: orgName
                        }
                        if (orgId && orgName) {
                            $.ajax({
                                url: "",
                                type: "post",
                                data: JSON.stringify(param),
                                contentType: "application/json",
                                success: function (data) {
                                    $('#organization_table').bootstrapTable('refresh');
                                    $.bootstrapGrowl(data.message, {type: data.status.toLowerCase()});
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}

$(document).ready(function () {
    OrganizationController.init();
});