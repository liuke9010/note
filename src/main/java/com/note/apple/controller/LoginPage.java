package com.note.apple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LiuKe
 * @Date 2019/6/29  1:19
 * @Desc    配置登录页访问路径
 */
@Controller
@CrossOrigin
public class LoginPage {
    @RequestMapping("/login")
    public String login(){
        /**
         * 获取当前用户的访问地址，后期可记录时间，IP等数据到数据库
         */



        return "login.html";
    }
}
