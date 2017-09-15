package com.zhang.dto;

/**
 * Created by zhenghua.zhang on 2017/9/7.
 */
public class RoleResourceDto {
    private Long roleId;
    private Long resourceId;

    public RoleResourceDto(Long roleId, Long resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getRoleId() {

        return roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
