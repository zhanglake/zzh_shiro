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
    @ResponseBody
    public Resource addPage(@PathVariable("parentId") Long parentId, Model model) {
        Resource parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        return parent;
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result createResource(@RequestBody ResourceDto dto) {
        resourceService.createOrUpdateResource(dto);
        return new Result("新增节点成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{resourceId}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteResource(@PathVariable("resourceId") Long id) {
        resourceService.deleteResource(id);
        return new Result("删除节点成功");
    }

    /**
     * 修改
     * @param dto
     * @param model
     * @return
     */
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateResource(@RequestBody ResourceDto dto, Model model) {
        resourceService.createOrUpdateResource(dto);
        return new Result("修改节点成功");
    }

}