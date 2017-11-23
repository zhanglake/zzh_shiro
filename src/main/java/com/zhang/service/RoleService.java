package com.zhang.service;

import com.zhang.dto.RoleEditDto;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Page;
import com.zhang.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface RoleService {

    Role findOne(Long roleId);

    Set<String> findPermissions(List<Long> roleIds);

    Set<String> findRoles(Long... roleIds);

    List<Role> findAll();

    Page findAllPageable(Page page, TableRequest request);

    void deleteOne(Long id);

    void changeStatus(Long id, Boolean status);

    Role createOrUpdateRole(RoleEditDto dto);

    List<Role> findForAutoComplete(String name);

    List<Role> findByIds(List<Long> roleIds);

}