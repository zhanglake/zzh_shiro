package com.zhang.entity;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
public class Organization extends BaseEntity {
    private Long id;
    private String name;
    private Long parentId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }
}
