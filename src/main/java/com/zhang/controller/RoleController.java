package com.zhang.controller;

import com.zhang.dto.RoleEditDto;
import com.zhang.dto.Select2Dto;
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
import java.util.ArrayList;
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

    /**
     * 角色列表
     * @param request
     * @return
     */
    @RequiresPermissions("role:view")
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    @ResponseBody
    public Page table(@RequestBody TableRequest request) {
        Page<Role> rolePage = new Page<Role>(request.getPageSize(), request.getPageNumber());
        Page page = roleService.findAllPageable(rolePage, request);
        return page;
    }

    /**
     * 新增 -- 页面
     * @param model
     * @return
     */
    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("op", "新增");
        return "role/role-edit";
    }

    /**
     * 新增角色
     * @param dto
     * @param model
     * @return
     */
    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody RoleEditDto dto, Model model) {
        roleService.createOrUpdateRole(dto);
        model.addAttribute("msg", "新增成功");
        return new Result();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRole(@RequestParam Long id) {
        roleService.deleteOne(id);
        return new Result();
    }

    /**
     * 禁用 / 启用
     * @param id
     * @param status
     * @return
     */
    @RequiresPermissions("role:update")
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Result changeAvailableStatus(@RequestParam Long id, @RequestParam Boolean status) {
        roleService.changeStatus(id, status);
        return new Result();
    }

    /**
     * 修改 - 页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("role:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePage(@RequestParam Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "role/role-edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody RoleEditDto dto, Model model) {
        roleService.createOrUpdateRole(dto);
        model.addAttribute("msg", "修改成功");
        return new Result();
    }

    /**
     * 为autocomplete控件查找
     * @param name
     * @return
     */
    @RequiresPermissions("role:view")
    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public List<Select2Dto> findForAutoComplete(@RequestBody String name) {
        List<Role> roles = roleService.findForAutoComplete(name);
        List<Select2Dto> dtos = new ArrayList<Select2Dto>();
        for (Role role : roles) {
            Select2Dto dto = new Select2Dto(role.getId(), role.getName(), null);
            dtos.add(dto);
        }
        return dtos;
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
