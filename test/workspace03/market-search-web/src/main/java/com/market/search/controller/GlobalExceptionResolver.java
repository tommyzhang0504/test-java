package com.market.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        logger.info("进入全局异常处理器~~~~");
        logger.debug("测试handler的类型："+handler.getClass());

        //控制台打印异常，同时将日志写入文件
        e.printStackTrace();
        logger.error("系统发生异常", e);

        //发邮件，发短信，
        //jmail     //相当于客户端
        //第三方短信运营商，以restful等形式发送短消息

        //展示错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","你的电脑被黑了吧。。。请等等再试");
        modelAndView.setViewName("/error/exception");
        return modelAndView;
    }
}
