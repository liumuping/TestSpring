package com.lhf.exam.service;

import com.lhf.exam.pojo.Order;
import com.lhf.exam.pojo.OrderFood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderService {
    List<Order> getOrder(@Param("start") int start, @Param("length") int length);
    boolean insertOrder(Order order);
    int getOrderId(int deskId);
    boolean insertOrderFood(@Param("orderId") int orderId, @Param("foodId") int foodId);
    List<OrderFood> getOrderFood(int orderId);
    boolean finishOrder(int orderId);
    int getCount();

}
