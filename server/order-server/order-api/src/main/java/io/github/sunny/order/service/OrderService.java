/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.order.service;


import javax.xml.ws.Response;

/**
 * @author zhaoyunxing
 * @date: 2019-08-27 15:39
 * @des:
 */
public interface OrderService {


    Response<?> findById(String id);
}
