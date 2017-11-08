package com.zhang.controller;

import com.zhang.entity.Organization;
import com.zhang.service.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequiresPermissions("organization:view")
    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public List<Organization> findAll(@RequestBody String name) {
        return organizationService.findForAutoComplete(name);
    }

}
