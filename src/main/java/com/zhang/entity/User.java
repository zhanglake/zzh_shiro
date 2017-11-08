package com.zhang.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public class User extends BaseEntity {

    private Long id;

//    @Column(name = "organization_id", length = 50)
    private Organization organization; //所属公司

    private String username;

    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户拥有的角色
     */
    private List<Role> roles;

    private Boolean locked = Boolean.FALSE;

    public void setId(Long id) {
        this.id = id;
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

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getId() {

        return id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {

        return organization;
    }
}
