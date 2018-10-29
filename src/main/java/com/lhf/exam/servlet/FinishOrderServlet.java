package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.DeskDaoImpl;
import com.lhf.exam.dao.impl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "FinishOrderServlet", urlPatterns = "/FinishOrderServlet")
public class FinishOrderServlet extends HttpServlet {
    DeskDaoImpl deskDao = new DeskDaoImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String deskname = request.getParameter("deskname");
        String pageNum = request.getParameter("pageNum");
        request.getSession().setAttribute("pageold", pageNum);
        orderDao.finishOrder(orderId);
        deskDao.updateDeskStateByName(deskname);
        response.sendRedirect("/GetOrderServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }
}
