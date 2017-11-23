package com.zhang.service;

import com.alibaba.druid.util.StringUtils;
import com.zhang.dao.RoleDao;
import com.zhang.dto.RoleEditDto;
import com.zhang.dto.RoleResourceDto;
import com.zhang.dto.RoleTableDto;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Page;
import com.zhang.entity.Resource;
import com.zhang.entity.Result;
import com.zhang.entity.Role;
import com.zhang.util.JoinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;

    @Override
    public Role findOne(Long roleId) {
        Role role = roleDao.findOne(roleId);
        return role;
    }

    @Override
    public Set<String> findPermissions(List<Long> roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = this.findOne(roleId);
            if(role != null) {
                List<Resource> resources = role.getResources();
                for (Resource resource : resources) {
                    resourceIds.add(resource.getId());
                }
//                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = this.findOne(roleId);
            if(role != null) {
                roles.add(role.getName());
            }
        }
        return roles;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Page findAllPageable(Page page, TableRequest request) {
        List<RoleTableDto> dtos = new ArrayList<RoleTableDto>();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idName", "r1.role_id");
            params.put("limit_position", 0);
            if (!StringUtils.isEmpty(request.getSearchText())) {
                params.put("searchText", "%" + request.getSearchText() + "%");
            }
            page.setParams(params);
            List<Role> roles = roleDao.selectBySelective(page);
            for (Role role : roles) {
                RoleTableDto dto = new RoleTableDto();
                dto.setRole(role);
                List<String> resStrs = new ArrayList<String>();
                for (Resource resource : role.getResources()) {
                    if (null != resource.getId() && null != resource.getName()) {
                        resStrs.add(resource.getName());
                    }
                }
                if (resStrs.size() == 0) {
                    role.setResources(new ArrayList<Resource>());
                }
                dto.setResourceStr(JoinUtil.join(resStrs));
                dtos.add(dto);
            }
            page.setResults(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error");
        }
        return page;
    }

    @Override
    public void deleteOne(Long id) {
        roleDao.updateToDelete(id);
    }

    @Override
    public void changeStatus(Long id, Boolean status) {
        if (status) {
            roleDao.changeToAvailable(id);
        } else {
            roleDao.changeToUnavailable(id);
        }
    }

    @Override
    public Role createOrUpdateRole(RoleEditDto dto) {
        Role role = null;
        if (null == dto.getId()) {
            // 新增
            role = new Role();
            role.setName(dto.getRoleName());
            role.setDescription(dto.getDescription());
            role.setAvailable(Boolean.TRUE);
            roleDao.createRole(role);
            // 插入中间表
            List<RoleResourceDto> roleResourceDtos = new ArrayList<RoleResourceDto>();
            for (Long resourceId : dto.getResourceIds()) {
                if (null != role.getId()) {
                    RoleResourceDto roleResourceDto = new RoleResourceDto(role.getId(), resourceId);
                    roleResourceDtos.add(roleResourceDto);
                }
            }
            if (roleResourceDtos.size() > 0) {
                roleDao.addRoleResource(roleResourceDtos);
            }
        } else {
            // 修改
            role = this.findOne(dto.getId());
            role.setName(dto.getRoleName());
            role.setDescription(dto.getDescription());
            role.setModifiedDate(new Date());
            role.setModifiedBy("系统修改");
            roleDao.updateRole(role);
            // 修改中间表 -- 先删除后新增
            roleDao.deleteRoleResource(dto.getId());
            List<RoleResourceDto> roleResourceDtos = new ArrayList<RoleResourceDto>();
            for (Long resourceId : dto.getResourceIds()) {
                if (null != role.getId()) {
                    RoleResourceDto roleResourceDto = new RoleResourceDto(role.getId(), resourceId);
                    roleResourceDtos.add(roleResourceDto);
                }
            }
            if (roleResourceDtos.size() > 0) {
                roleDao.addRoleResource(roleResourceDtos);
            }
        }
        return role;
    }

    @Override
    public List<Role> findForAutoComplete(String name) {
        List<Role> roles = null;
        if (null == name || "".equals(name)) {
            roles = roleDao.findAll();
            return roles;
        }
        String roleName = "%" + name + "%";
        roles = roleDao.findLikeName(roleName);
        for (Role role : roles) {
            role.setResources(null);
        }
        return roles;
    }

    @Override
    public List<Role> findByIds(List<Long> roleIds) {
        return roleDao.findByIds(roleIds);
    }

}
