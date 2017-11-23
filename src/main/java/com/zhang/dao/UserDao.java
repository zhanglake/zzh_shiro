package com.zhang.dao;

import com.zhang.dto.TableRequest;
import com.zhang.dto.UserUpdateDto;
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

    /**
     * 更新user信息
     * @param dto
     */
    void updateUser(UserUpdateDto dto);

    /**
     * 删除用户原来关联的角色
     * @param userId
     */
    void deleteUserRole(Long userId);

    /**
     * 插入user-role关系
     * @param list
     */
    void insertUserRole(List list);

    /**
     * 新增用户
     * @param user
     */
    void createUser(User user);

}
