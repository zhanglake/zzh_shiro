package com.zhang.controller;

import com.zhang.dto.ResourceDto;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Page;
import com.zhang.entity.Resource;
import com.zhang.entity.Result;
import com.zhang.entity.Role;
import com.zhang.service.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/10/14.
 */
@Controller
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 权限 -- 页面
     * @param model
     * @return
     */
    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model) {
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList", resourceList);
        return "resource/resource-list";
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String addPage(@PathVariable("parentId") Long parentId, Model model) {
        Resource parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        Resource child = new Resource();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("resource", child);
        return "resource/resource-add";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result createResource(@RequestBody ResourceDto dto, Model model) {
        resourceService.createOrUpdateResource(dto);
        model.addAttribute("msg", "新增成功");
        return new Result();
    }

}