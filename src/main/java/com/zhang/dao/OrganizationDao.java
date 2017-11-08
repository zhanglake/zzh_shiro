package com.zhang.dao;

import com.zhang.entity.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
@Repository("organizationDao")
public interface OrganizationDao {

    List<Organization> findAll();

    List<Organization> findLikeName(String name);

}
