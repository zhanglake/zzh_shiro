package com.zhang.dao;

import com.zhang.entity.Page;
import com.zhang.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Repository
public interface RoleDao {

    Role findOne(Long roleId);

    List<Role> findAll();

    List<Role> selectBySelective(Page page);

    void updateToDelete(Long id);

    void changeToAvailable(Long id);

    void changeToUnavailable(Long id);

    void createRole(Role role);

    void addRoleReource(List list);
}
