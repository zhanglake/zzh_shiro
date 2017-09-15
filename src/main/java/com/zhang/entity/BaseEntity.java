package com.zhang.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhenghua.zhang on 2017/9/5.
 */
public class BaseEntity implements Serializable {

    private Date createdDate = new Date();

    private String createdBy = "系统";

    private Date modifiedDate = new Date();

    private String modifiedBy = "系统";

    /**
     * 软删除
     */
    private Boolean deleted = Boolean.FALSE;

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
