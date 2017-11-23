package com.zhang.dao;

import com.zhang.dto.TableRequest;
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

    Organization findOne(Long id);

    List<Organization> findAllPageable(TableRequest request);

    Long findAllCount(TableRequest request);

}
