/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.sunny.core.msg.Response;
import io.github.sunny.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author zhaoyunxing
 * @date: 2019-08-27 17:16
 * @des:
 */
@RestController
@Slf4j
public class AppController {

    private final OrderService orderService;

    @Autowired
    public AppController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/order")
    public Response getOrder() {
        return orderService.findById("2");
    }

    @GetMapping("/git")
    public Response getGitInfo() {

        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("git.properties");
        try {
            properties.load(in);
        } catch (IOException ex) {
            log.error("加载[git.properties]属性文件异常:{}", ex.getLocalizedMessage());
        }
        return Response.createSuccess("加载git.properties成功", JSONObject.toJSON(properties));
    }
}
