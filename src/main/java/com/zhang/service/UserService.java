package com.zhang.service;

import com.zhang.entity.User;

import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface UserService {

    User findByUsername(String username);

    Set<String> findPermissions(String username);

    Set<String> findRoles(String username);
}
