package com.zhang.service;

import com.zhang.dto.TableRequest;
import com.zhang.entity.Organization;
import com.zhang.entity.Page;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
public interface OrganizationService {

    List<Organization> findAll();

    List<Organization> findForAutoComplete(String name);

    Organization findOne(Long id);

    Page findAllPageable(Page page, TableRequest request);

}
