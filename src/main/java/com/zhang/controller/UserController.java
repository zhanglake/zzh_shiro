package com.zhang.controller;

import com.zhang.dto.TableRequest;
import com.zhang.dto.UserCreateDto;
import com.zhang.dto.UserUpdateDto;
import com.zhang.entity.Page;
import com.zhang.entity.Result;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhenghua.zhang on 2017/11/1.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 页面
     * @param model
     * @return
     */
    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model) {
        return "user/user";
    }

    /**
     * 用户列表
     * @param request
     * @return
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Page findAllUsers(@RequestBody TableRequest request) {
//        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");
        Page<User> page = new Page<User>(request.getPageSize(), request.getPageNumber());
        page = userService.findAllPageable(page, request);
        return page;
    }

    /**
     * 创建用户信息
     * @param dto
     * @return
     */
    @RequiresPermissions("user:create")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody UserCreateDto dto) {
        userService.createUser(dto);
        return new Result();
    }

    /**
     * 修改用户信息
     * @param dto
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody UserUpdateDto dto) {
        if (null != dto.getId() && null != dto.getUserName()) {
            userService.updateUser(dto);
            return new Result("修改成功");
        }
        return new Result(500, Result.ResultStatus.WARNING, "修改失败，请填写必填项目");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiresPermissions("user:delete")
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteOne (@PathVariable("id") Long id) {
        userService.deleteOne(id);
        return new Result("删除成功");
    }

    /**
     * 锁定/解锁
     *
     * @param id
     * @param status
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "{id}/{status}/status", method = RequestMethod.GET)
    @ResponseBody
    public Result changeStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status) {
        String mes = "";
        userService.changeStatus(id, status);
        if (status) {
            mes = "锁定成功";
        } else {
            mes = "解锁成功";
        }
        return new Result(mes);
    }

}
