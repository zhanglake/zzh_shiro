package com.zhang.dto;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/23.
 */
public class UserDto {
    private Long id;
    private String userName;
    private String organizationName;
    private String roleNames;
    private Boolean locked;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public Boolean getLocked() {
        return locked;
    }
}
