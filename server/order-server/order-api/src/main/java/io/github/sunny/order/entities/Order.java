/**
 * Copyright(C) 2019 Hangzhou zhaoyunxing Technology Co., Ltd. All rights reserved.
 */
package io.github.sunny.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author zhaoyunxing
 * @date: 2019-08-27 14:37
 * @des:
 */
@Data
@ToString
@AllArgsConstructor
public class Order {

    private String id;
    /**
     * 订单名称
     */
    private String name;
    /**
     * 订单描述
     */
    private String desc;
    /**
     * 订单创建时间
     */
    private Date createTime;
}
