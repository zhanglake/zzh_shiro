package com.zhang.controller;

import com.zhang.dto.RoleEditDto;
import com.zhang.dto.TableRequest;
import com.zhang.entity.Page;
import com.zhang.entity.Result;
import com.zhang.entity.Role;
import com.zhang.service.ResourceService;
import com.zhang.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/9/2.
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model) {
//        model.addAttribute("roleList", roleService.findAll());
        return "role/role-list";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    @ResponseBody
    public Page table(@RequestBody TableRequest request) {
        Page<Role> rolePage = new Page<Role>(request.getPageSize(), request.getPageNumber());
        Page page = roleService.findAllPageable(rolePage, request);
        return page;
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("op", "新增");
        return "role/role-edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody RoleEditDto dto, Model model) {
        roleService.createOrUpdateRole(dto);
        model.addAttribute("msg", "新增成功");
        return new Result();
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRole(@RequestParam Long id) {
        roleService.deleteOne(id);
        return new Result();
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Result changeAvailableStatus(@RequestParam Long id, @RequestParam Boolean status) {
        roleService.changeStatus(id, status);
        return new Result();
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePage(@RequestParam Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "role/role-edit";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
