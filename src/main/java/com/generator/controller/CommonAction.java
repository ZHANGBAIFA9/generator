package com.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2021/3/5 17:43
 * @Description:
 */
@Controller
public class CommonAction {
    /**
     * @功能描述 进入主页面的跳转
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
