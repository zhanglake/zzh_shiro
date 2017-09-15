var TablePlugin = {
    /**
     * 修改和删除图标
     * @returns {string}
     */
    operateFormatter: function () {
        return [
            '<shiro:hasPermission name="role:update"><a class="modify" href="javascript:void(0)" title="修改">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a></shiro:hasPermission>  ',
            '<a class="remove" href="javascript:void(0)" title="删除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('  ');
    },
    /**
     * 修改和删除图标的操作
     */
    operateEvents: function () {

    }
}