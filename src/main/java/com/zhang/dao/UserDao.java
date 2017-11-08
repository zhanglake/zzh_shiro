package com.zhang.dao;

import com.zhang.dto.TableRequest;
import com.zhang.entity.Page;
import com.zhang.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Repository("userDao")
public interface UserDao {

    User findByUserNameWithRole(String userName);

    List<User> findAllUsers();

    List<User> findAllPageable(TableRequest request);

    Long findAllCount(TableRequest request);

    void deleteOne(Long id);

    void changeStatus(Long id, Boolean status);
}
