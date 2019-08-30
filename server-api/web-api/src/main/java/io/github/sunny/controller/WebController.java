/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.controller;

import io.github.sunny.core.msg.Response;
import io.github.sunny.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyunxing
 * @date: 2019-08-30 11:01
 * @des:
 */
@Slf4j
@RestController
public class WebController {

    private final OrderService orderService;
    @Autowired
    public WebController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/order")
    public Response getOrder() {
        return orderService.findById("2");
    }

}
