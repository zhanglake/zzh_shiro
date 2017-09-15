package com.zhang.entity;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public class Role extends BaseEntity {
    private Long id;                //编号
    private String name;            //角色标识 程序中判断使用,如"admin"
    private String description;     //角色描述,UI界面显示使用
    private List<Resource> resources; //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getId() {

        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public Boolean getAvailable() {
        return available;
    }
}
