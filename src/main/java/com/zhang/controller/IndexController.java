package com.zhang.controller;

import com.zhang.bind.annotation.CurrentUser;
import com.zhang.entity.Resource;
import com.zhang.entity.User;
import com.zhang.service.ResourceService;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * Created by zhenghua.zhang on 2017/8/22.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    /**
     * 登陆成功跳转到主页
     * @param loginUser
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }
}
