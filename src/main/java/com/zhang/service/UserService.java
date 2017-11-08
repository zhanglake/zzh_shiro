package com.zhang.service;

import com.zhang.dto.TableRequest;
import com.zhang.dto.UserDto;
import com.zhang.entity.Page;
import com.zhang.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface UserService {

    User findByUsername(String username);

    Set<String> findPermissions(String username);

    Set<String> findRoles(String username);

    List<User> findAllUsers();

    Page findAllPageable(Page page, TableRequest request);

    void deleteOne(Long id);

    void changeStatus(Long id, Boolean status);
}
