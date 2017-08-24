package com.zhang.service;

import com.zhang.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
public interface ResourceService {

    Resource findOne(Long resourceId);

    List<Resource> findAll();

    Set<String> findPermissions(Set<Long> resourceIds);

    List<Resource> findMenus(Set<String> permissions);

}
