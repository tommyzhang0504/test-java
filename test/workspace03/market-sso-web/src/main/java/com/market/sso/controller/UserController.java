package com.market.sso.controller;

import com.market.pojo.MarketResult;
import com.market.pojo.TbUser;
import com.market.sso.service.UserService;
import com.market.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Value("${TT_TOKEN}")
    private String TT_TOKEN;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public MarketResult checkData(@PathVariable String param, @PathVariable Integer type) {
        MarketResult result = userService.checkData(param, type);

        return result;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public MarketResult userRegister(TbUser tbUser) {
        MarketResult result = userService.register(tbUser);
        return result;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public MarketResult userLogin(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return MarketResult.build(400, "用户名和密码不能为空");
        }
        MarketResult result = userService.login(username, password);
        //当用户登陆成功后，再设置cookie
        if (result.getStatus()==200) {
            CookieUtils.setCookie(request, response, TT_TOKEN, result.getData().toString());
        }
        return result;
    }

    @RequestMapping(value = "user/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public MarketResult getUserByToken(@PathVariable String token) {
        MarketResult result = userService.getUserByToken(token);
        return result;
    }

    @RequestMapping(value = "user/logout/{token}", method = RequestMethod.GET)
    @ResponseBody
    public MarketResult logoutByDeletingToken(@PathVariable String token) {

        MarketResult result = userService.logoutByDeletingToken(token);
        return result;
    }

}
