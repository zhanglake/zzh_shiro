package com.zhang.dto;

/**
 * Created by zhenghua.zhang on 2017/10/24.
 */
public class ResourceDto {
    private Long id;
    private String name;
    private String type;
    private Long parentId;

    // 地址
    private String url;

    //权限字符串
    private String permission;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getUrl() {
        return url;
    }

    public String getPermission() {
        return permission;
    }
}
