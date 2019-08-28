/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.order.service.impl;


import io.github.sunny.core.msg.Response;
import io.github.sunny.order.entities.Order;
import io.github.sunny.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


/**
 * @author zhaoyunxing
 * @date: 2019-08-27 16:03
 * @des:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Response<?> findById(String id) {
        Order order = new Order(id, "k70海盗船", "机械键盘", new Date());
        return Response.createSuccess("根据id:%s获取数据成功", order, id);
    }
}
