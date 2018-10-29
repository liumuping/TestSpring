package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.OrderDaoImpl;
import com.lhf.exam.pojo.OrderFood;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "GetOrderFoodServlet",urlPatterns = "/GetOrderFoodServlet")
public class GetOrderFoodServlet extends HttpServlet {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println();
        List<OrderFood> orderFoods = orderDao.getOrderFood(orderId);
        for (OrderFood orderFood : orderFoods) {
            System.out.println(orderFood);
        }
        request.setAttribute("orderFoods", orderFoods);
        request.getRequestDispatcher("/WEB-INF/orderDetail.jsp").forward(request,response);
    }
}
