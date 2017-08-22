package com.zhang.dao;

import com.zhang.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Repository("userDao")
public interface UserDao {

    User findByUsername(String userName);

}
