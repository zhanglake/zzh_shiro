package com.zhang.dto;

/**
 * Created by zhenghua.zhang on 2017/8/23.
 */
public class RoleDto {
    private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resourceIds; //拥有的资源
    private Boolean available = Boolean.FALSE;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getId() {

        return id;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }
}
