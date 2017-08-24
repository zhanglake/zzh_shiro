package com.zhang.dao;

import com.zhang.dto.RoleDto;
import org.springframework.stereotype.Repository;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Repository
public interface RoleDao {

    RoleDto findOne(Long roleId);

}
