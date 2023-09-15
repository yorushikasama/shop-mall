package com.gk.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gk.order.model.Order;

public interface OrderService extends IService<Order> {
    /**
     * 添加订单
     * @param order
     * @return
     */
    Boolean add(Order order);
}
