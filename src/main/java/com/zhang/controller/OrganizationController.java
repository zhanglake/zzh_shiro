package com.zhang.controller;

import com.zhang.dto.Select2Dto;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Organization;
import com.zhang.entity.Page;
import com.zhang.entity.User;
import com.zhang.service.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/8.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 页面
     * @param model
     * @return
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model) {
        return "organization/organization";
    }

    /**
     * 组织机构列表
     * @param request
     * @return
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Page findAllUsers(@RequestBody TableRequest request) {
        Page<Organization> page = new Page<Organization>(request.getPageSize(), request.getPageNumber());
        page = organizationService.findAllPageable(page, request);
        return page;
    }

    /**
     * 获取数据给select2下拉选
     * @param name
     * @return
     */
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public List<Select2Dto> findAll(@RequestBody String name) {
        List<Organization> organizations = organizationService.findForAutoComplete(name);
        List<Select2Dto> select2Dtos = new ArrayList<Select2Dto>();
        for (Organization org : organizations) {
            Select2Dto dto = new Select2Dto(org.getId(), org.getName(), null);
            select2Dtos.add(dto);
        }
        return select2Dtos;
    }

}
