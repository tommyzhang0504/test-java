package com.market.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController {

    @RequestMapping("/page/register")
    public String pageRegister() {

        return "register";
    }
    @RequestMapping("/page/login")
    public String pageLogin() {

        return "login";
    }
}
