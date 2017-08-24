package com.zhang.dto;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/23.
 */
public class UserDto {
    private Long id; //编号
    private Long organizationId; //所属公司
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private String roleIds; //拥有的角色列表
    private Boolean locked;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public Boolean getLocked() {
        return locked;
    }
}
