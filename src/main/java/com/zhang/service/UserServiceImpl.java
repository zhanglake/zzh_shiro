package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.dto.TableRequest;
import com.zhang.dto.UserDto;
import com.zhang.entity.Page;
import com.zhang.entity.Role;
import com.zhang.entity.User;
import com.zhang.util.JoinUtil;
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

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public Page findAllPageable(Page page, TableRequest request) {
        request.setPageNumber(request.getPageNumber() - 1);
        if (null != request.getSearchText()) {
            request.setSearchText("%" + request.getSearchText() + "%");
        }
        List<User> userList = userDao.findAllPageable(request);
        Long count = userDao.findAllCount(request);
        List<UserDto> userDtos = new ArrayList<UserDto>();
        UserDto dto = null;
        for (User user : userList) {
            dto = new UserDto();
            dto.setId(user.getId());
            dto.setLocked(user.getLocked());
            dto.setUserName(user.getUsername());
            List<String> roleNameList = new ArrayList<String>();
            for (Role role : user.getRoles()) {
                roleNameList.add(role.getName());
            }
            dto.setRoleNames(JoinUtil.join(roleNameList));
            dto.setOrganizationName(user.getOrganization().getName());
            userDtos.add(dto);
        }
        page.setResults(userDtos);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void deleteOne(Long id) {
        userDao.deleteOne(id);
    }

    @Override
    public void changeStatus(Long id, Boolean status) {
        if (status) {
            status = Boolean.FALSE;
        } else {
            status = Boolean.TRUE;
        }
        userDao.changeStatus(id, status);
    }
}
