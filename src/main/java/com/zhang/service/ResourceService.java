package com.zhang.service;

import com.zhang.dto.ResourceDto;
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

    Set<String> findPermissions(List<Resource> resources);

    List<Resource> findMenus(Set<String> permissions);

    /**
     * 新增或删除
     * @param dto
     */
    void createOrUpdateResource(ResourceDto dto);

    /**
     * 删除
     * @param id
     */
    void deleteResource(Long id);

}
