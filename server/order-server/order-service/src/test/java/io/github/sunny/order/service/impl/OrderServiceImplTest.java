package io.github.sunny.order.service.impl;

import io.github.sunny.core.msg.Response;
import io.github.sunny.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author zhaoyunxing
 * @date: 2019-08-28 16:23
 * @des:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderServiceImpl.class})
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void findById() {
        Response<?> order = orderService.findById("2");
        Assert.isTrue(!order.getSuccess(), "没有查到订单为2的信息");
    }
}