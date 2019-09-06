/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import io.github.sunny.core.msg.Response;
import io.github.sunny.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

        try {
            Properties properties = new Properties();
            InputStream in = getClass().getClassLoader().getResourceAsStream("git.properties");
            properties.load(in);
            return Response.createSuccess("加载git.properties成功", JSONObject.toJSON(properties));
        } catch (Exception ex) {
            log.error("加载[git.properties]属性文件异常:{}", ex.getLocalizedMessage());
            return Response.createError("加载[git.properties]属性文件异常,请使用【java -jar】方式启动项目", 404);
        }

    }

    /**
     * 动态修改日志级别
     *
     * @param pgk   包路径 eg:com.io.github.sunny
     * @param level 日志级别 ALL、TRACE、DEBUG、INFO、WARN、ERROR
     * @return {@link Response}
     */
    @PostMapping("/log/{pgk}/{level}")
    public Response setLogLevel(@PathVariable("pgk") String pgk, @PathVariable("level") String level) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger(pgk).setLevel(Level.valueOf(level));

        return Response.createSuccess("成功修改包[%s]日志级别为：[%s]", null, pgk, level);
    }

    @GetMapping("/log")
    public void getLog() {
        log.info("log info");
        log.debug("log debug");
        log.error("log error");
    }
}
