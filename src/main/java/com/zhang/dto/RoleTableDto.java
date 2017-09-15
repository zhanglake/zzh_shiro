package com.zhang.dto;

import com.zhang.entity.Role;

/**
 * Created by zhenghua.zhang on 2017/9/5.
 */
public class RoleTableDto {
    private String resourceStr;
    private Role role;

    public void setResourceStr(String resourceStr) {
        this.resourceStr = resourceStr;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getResourceStr() {

        return resourceStr;
    }

    public Role getRole() {
        return role;
    }
}
