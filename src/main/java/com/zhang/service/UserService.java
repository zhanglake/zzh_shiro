package com.zhang.service;

import com.zhang.entity.User;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface UserService {

    public User findByUsername(String username);

}
