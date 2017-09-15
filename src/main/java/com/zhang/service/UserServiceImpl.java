package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.dto.UserDto;
import com.zhang.entity.Role;
import com.zhang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;

    @Override
    public User findByUsername(String userName) {
        User user = userDao.findByUserNameWithRole(userName);
        return user;
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = this.findByUsername(username);
        if(null == user) {
            return Collections.EMPTY_SET;
        }
        List<Long> roleIds = new ArrayList<Long>();
        for (Role role : user.getRoles()) {
            roleIds.add(role.getId());
        }
        return roleService.findPermissions(roleIds);
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = this.findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        Set<String> roleNameSet = new HashSet<String>();
        for (Role role : user.getRoles()) {
            roleNameSet.add(role.getName());
        }
        return roleNameSet;
    }
}
