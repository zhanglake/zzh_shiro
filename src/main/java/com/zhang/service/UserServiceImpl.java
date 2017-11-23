package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.dto.*;
import com.zhang.entity.Organization;
import com.zhang.entity.Page;
import com.zhang.entity.Role;
import com.zhang.entity.User;
import com.zhang.util.JoinUtil;
import com.zhang.util.PasswordUtil;
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
    @Autowired
    private OrganizationService organizationService;

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

    @Override
    public void updateUser(UserUpdateDto userUpdateDto) {
        userUpdateDto.setModifiedDate(new Date());
        userUpdateDto.setModifiedBy("系统");
        // 更改用户
        userDao.updateUser(userUpdateDto);
        Long userId = userUpdateDto.getId();
        // 更改用户-角色列表
        List<UserRoleDto> userRoleDtos = new ArrayList<UserRoleDto>();
        for (Long roleId : userUpdateDto.getRoleIds()) {
            UserRoleDto dto = new UserRoleDto(userId, roleId);
            userRoleDtos.add(dto);
        }
        userDao.deleteUserRole(userId);
        userDao.insertUserRole(userRoleDtos);
    }

    @Override
    public void createUser(UserCreateDto dto) {
        List<Long> roleIds = dto.getRoleIds();
        List<Role> roles = roleService.findByIds(roleIds);
        Organization organization = organizationService.findOne(dto.getOrganizationId());

        User user = new User();
        user.setUsername(dto.getUserName());
        user.setOrganization(organization);
        user.setRoles(roles);
        String salt = PasswordUtil.createSaltByShiro();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.createPwdByShiro("111111", salt, dto.getUserName()));
        user.setRoles(roles);
        userDao.createUser(user);
        List<UserRoleDto> userRoleDtos = new ArrayList<UserRoleDto>();
        for (Role role : roles) {
            UserRoleDto userRoleDto = new UserRoleDto(user.getId(), role.getId());
            userRoleDtos.add(userRoleDto);
        }
        userDao.insertUserRole(userRoleDtos);
    }
}
