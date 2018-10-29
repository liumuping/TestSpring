package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.OrderDaoImpl;
import com.lhf.exam.pojo.Order;
import com.lhf.exam.pojo.OrderFood;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "GetOrderServlet",urlPatterns = "/GetOrderServlet")
public class GetOrderServlet extends HttpServlet {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<OrderFood> orderFoods = orderDao.getOrderFood(orderId);
        request.setAttribute("orderFoods", orderFoods);
        request.getRequestDispatcher("/WEB-INF/orderDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = orderDao.getCount();
        int pageNum = 4;
        int total = count/pageNum;
        if(count%pageNum!=0){
            total++;
        }

        //获取当前页码页面的数据
        String pageNumber = request.getParameter("pageNumber");
        String method = request.getParameter("method");
        if (request.getParameter("pageNumber")!=null || "1".equals(method)){
            pageNumber = request.getParameter("pageNumber");
        }else {
            pageNumber = (String) request.getSession().getAttribute("pageold");
        }
        if(pageNumber==null||pageNumber.equals("")){
            pageNumber="1";
        }
        int start=0;//默认pageNumber=0+1开始检索
        //根据当前页码进行检索
        int number = Integer.parseInt(pageNumber);
        start=(number-1)*pageNum;
        List<Order> orders = orderDao.getOrder(start,pageNum);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/orderList.jsp").forward(request,response);
    }
}
