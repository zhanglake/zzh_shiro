package com.zhang.dto;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/17.
 */
public class UserCreateDto {
    private String userName;
    private Long organizationId;
    private List<Long> roleIds;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserName() {
        return userName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }
}
