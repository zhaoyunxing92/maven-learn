/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.controller;

import io.github.sunny.core.msg.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhaoyunxing
 * @date: 2019-08-27 17:16
 * @des:
 */
@RestController
@RequestMapping("/order")
public class AppController {



    @GetMapping
    public Response getOrder() {
        return null;
    }
}
