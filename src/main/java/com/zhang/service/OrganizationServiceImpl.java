package com.zhang.service;

import com.zhang.dao.OrganizationDao;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Organization;
import com.zhang.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public List<Organization> findForAutoComplete(String name) {
        List<Organization> organizations = null;
        if (null == name || "".equals(name)) {
            organizations = organizationDao.findAll();
            return organizations;
        }
        String orgName = "%" + name + "%";
        organizations = organizationDao.findLikeName(orgName);
        return organizations;
    }

    @Override
    public Organization findOne(Long id) {
        Organization organization = organizationDao.findOne(id);
        return organization;
    }

    @Override
    public Page findAllPageable(Page page, TableRequest request) {
        request.setPageNumber(request.getPageNumber() - 1);
        if (null != request.getSearchText()) {
            request.setSearchText("%" + request.getSearchText() + "%");
        }
        List<Organization> organizations = organizationDao.findAllPageable(request);
        Long count = organizationDao.findAllCount(request);
        page.setResults(organizations);
        page.setTotalRecord(count);
        return page;
    }
}
