package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.dto.UserDto;
import com.zhang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

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
        UserDto userDto = userDao.findByUserName(userName);
        return new User(userDto);
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = this.findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = this.findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }
}
