package com.zhang.service;

import com.zhang.entity.Organization;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
public interface OrganizationService {

    List<Organization> findAll();

    List<Organization> findForAutoComplete(String name);

}
