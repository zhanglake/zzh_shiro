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
        // 删除
        $(".deleteBtn").click(function() {
            if(confirm("确认删除吗?")) {
                alert("删你妹！");
            }
        });
    },
    dialogAdd: function () {
        var html_ = $("#dialog_add").html();
        $.confirm({
            title: '添加子节点',
            content: '<div id="dialog">' + html_ + '</div>',
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
                            permission: permission
                        }
                        $.ajax({
                            url: 'resource/create',
                            type: 'post',
                            data: data,
                            success: function (data) {
                                console.log(data);
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
    }
}

$(document).ready(function () {
    Resource.init();
});