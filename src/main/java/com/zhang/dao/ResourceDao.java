package com.zhang.dao;

import com.zhang.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Repository
public interface ResourceDao {

    Resource findOne(Long resourceId);

    List<Resource> findAll();
}
