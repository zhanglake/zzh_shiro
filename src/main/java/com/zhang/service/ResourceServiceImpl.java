package com.zhang.service;

import com.zhang.dao.ResourceDao;
import com.zhang.dto.ResourceDto;
import com.zhang.entity.Resource;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = this.findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public Set<String> findPermissions(List<Resource> resources) {
        Set<String> permissions = new HashSet<String>();
        for(Resource resource : resources) {
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = this.findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    @Override
    public void createOrUpdateResource(ResourceDto dto) {
        Long id = dto.getId();
        Resource resource = null;
        if (null == id) {
            resource = new Resource();
            resource.setName(dto.getName());
            resource.setPermission(dto.getPermission());
            resource.setUrl(dto.getUrl());
            resource.setParentId(dto.getParentId());
            resource.setAvailable(Boolean.TRUE);
            if ("menu".equals(dto.getType())) {
                resource.setType(Resource.ResourceType.menu);
            } else {
                resource.setType(Resource.ResourceType.button);
            }
            resourceDao.createResource(resource);
        } else {
            resource = new Resource();
            resource.setId(id);
            resource.setModifiedDate(new Date());
            resource.setModifiedBy("系统修改");
            resource.setName(dto.getName());
            resource.setPermission(dto.getPermission());
            resource.setUrl(dto.getUrl());
            if ("menu".equals(dto.getType())) {
                resource.setType(Resource.ResourceType.menu);
            } else {
                resource.setType(Resource.ResourceType.button);
            }
            resourceDao.updateResource(resource);
        }
    }

    @Override
    public void deleteResource(Long id) {
        resourceDao.deleteResource(id);
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

}
