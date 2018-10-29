package com.lhf.exam.service;

import com.lhf.exam.dao.IOrderDao;
import com.lhf.exam.pojo.Order;
import com.lhf.exam.pojo.OrderFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Scope("prototype")
public class OrderService implements IOrderService{
    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Order> getOrder(int start, int length) {
        List<Order> orders = new ArrayList<>();
        orders = orderDao.getOrder(start,length);
        return orders;
    }

    @Override
    public boolean insertOrder(Order order) {
        boolean flag = false;
        flag = orderDao.insertOrder(order);
        return flag;
    }

    @Override
    public int getOrderId(int deskId) {
        int flag = 0;
        flag = orderDao.getOrderId(deskId);
        return flag;
    }

    @Override
    public boolean insertOrderFood(int orderId, int foodId) {
        boolean flag = false;
        flag = orderDao.insertOrderFood(orderId,foodId);
        return flag;
    }

    @Override
    public List<OrderFood> getOrderFood(int orderId) {
        List<OrderFood> orderFoods = new ArrayList<>();
        orderFoods = orderDao.getOrderFood(orderId);
        return orderFoods;
    }

    @Override
    public boolean finishOrder(int orderId) {
        boolean flag = false;
        flag = orderDao.finishOrder(orderId);
        return flag;
    }

    @Override
    public int getCount() {
        int flag = 0;
        flag = orderDao.getCount();
        return flag;
    }
}
