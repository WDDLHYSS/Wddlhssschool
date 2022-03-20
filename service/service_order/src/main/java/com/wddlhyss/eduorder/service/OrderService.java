package com.wddlhyss.eduorder.service;

import com.wddlhyss.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-08
 */
public interface OrderService extends IService<Order> {

    String createrOrder(String courseId, String memberIdByJwtToken);
}
