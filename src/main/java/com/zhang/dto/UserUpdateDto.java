package com.zhang.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/17.
 */
public class UserUpdateDto {
    private Long id;
    private String userName;
    private Long organizationId;
    private List<Long> roleIds;
    private Date modifiedDate;
    private String modifiedBy;

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getId() {

        return id;
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
