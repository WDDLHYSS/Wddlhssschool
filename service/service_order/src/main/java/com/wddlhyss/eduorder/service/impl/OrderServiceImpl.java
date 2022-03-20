package com.wddlhyss.eduorder.service.impl;

import com.wddlhyss.commonutils.CourseWebVoOrder;
import com.wddlhyss.commonutils.UcenterMemberOrder;
import com.wddlhyss.eduorder.client.EduClient;
import com.wddlhyss.eduorder.client.UcneterClient;
import com.wddlhyss.eduorder.entity.Order;
import com.wddlhyss.eduorder.mapper.OrderMapper;
import com.wddlhyss.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wddlhyss.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcneterClient ucneterClient;

    @Override
    public String createrOrder(String courseId, String memberIdByJwtToken) {
        UcenterMemberOrder userInfoOrder = ucneterClient.getuserInfoOrder(memberIdByJwtToken);
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());

        order.setStatus(0);  //订单状态（0：未支付 1：已支付）

        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }

}
