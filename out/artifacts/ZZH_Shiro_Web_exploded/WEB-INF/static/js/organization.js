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
    operateEvents: function () {

    }
}

$(document).ready(function () {
    OrganizationController.init();
});