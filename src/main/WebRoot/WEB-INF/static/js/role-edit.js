var RoleEditController = {
    init: function () {
        // 树形资源列表
        $.fn.zTree.init($("#tree"), RoleEditController.setting, zNodesFunction());
        $("#menuBtn").click(RoleEditController.showMenu);
        // 保存
        $("#save").click(function () {
            var role_id = $("#id").val();
            var role_name = $("#name").val();
            var description = $("#description").val();
            var resourceIds = $("#resourceIds").val();
            var resourcesArray = resourceIds ? resourceIds.split(",") : [];
            var data = {
                id: role_id ? role_id : null,
                roleName: role_name,
                description: description,
                resourceIds: resourcesArray
            }
            if (role_id) {
                $.ajax({
                    url: "update",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (data) {
                        window.location = "../role";
                    }
                });
            } else {
                $.ajax({
                    url: "create",
                    type: "post",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (data) {
                        window.location = "../role";
                    }
                });
            }
        });
    },
    // 树状图配置
    setting: {
        check: {
            enable: true ,
            chkboxType: { "Y": "", "N": "" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: function () {
                RoleEditController.onCheck();
            }
        }
    },
    onCheck: function () {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getCheckedNodes(true),
            id = "",
            name = "";
        nodes.sort(function compare(a,b){
            return a.id - b.id;
        });
        for (var i=0, l=nodes.length; i<l; i++) {
            id += nodes[i].id + ",";
            name += nodes[i].name + ",";
        }
        if (id.length > 0 ) {
            id = id.substring(0, id.length-1);
        }
        if (name.length > 0 ) {
            name = name.substring(0, name.length-1);
        }
        $("#resourceIds").val(id);
        $("#resourceName").val(name);
        //RoleEditController.hideMenu();
    },
    showMenu: function () {
        var cityObj = $("#resourceName");
        var cityOffset = $("#resourceName").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", RoleEditController.onBodyDown);
    },
    hideMenu: function () {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", RoleEditController.onBodyDown);
    },
    onBodyDown: function () {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            RoleEditController.hideMenu();
        }
    }
}

$(document).ready(function () {
    RoleEditController.init();
});