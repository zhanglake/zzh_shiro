package com.zhang.dto;

import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/9/7.
 */
public class RoleEditDto implements Serializable {
    private Long id;
    private String roleName;
    private String description;
    private List<Long> resourceIds;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Long getId() {

        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public List<Long> getResourceIds() {
        return resourceIds;
    }
}
