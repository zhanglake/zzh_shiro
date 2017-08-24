package com.zhang.service;

import com.zhang.entity.Role;

import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface RoleService {

    public Role findOne(Long roleId);

    Set<String> findPermissions(Long[] roleIds);

    Set<String> findRoles(Long... roleIds);

}