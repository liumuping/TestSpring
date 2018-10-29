package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.DeskDaoImpl;
import com.lhf.exam.dao.impl.FoodDaoImpl;
import com.lhf.exam.dao.impl.OrderDaoImpl;
import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.Order;
import com.lhf.exam.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@WebServlet(name = "InsertOrderServlet", urlPatterns = "/InsertOrderServlet")
public class InsertOrderServlet extends HttpServlet {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    DeskDaoImpl deskDao = new DeskDaoImpl();
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String deskid = request.getParameter("desk");
        String custom = request.getParameter("custom");
        String tel = request.getParameter("tel");
        User user = (User) request.getSession().getAttribute("user");
        String[] ids = request.getParameterValues("foodid");
        Date date = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时
        System.out.println(time);
        List<Food> foods = (List<Food>) request.getSession().getAttribute("Allfoods");
        double priceAll = 0;
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < foods.size(); j++) {
                if (Integer.parseInt(ids[i]) == foods.get(j).getId()){
                    priceAll = priceAll+foods.get(j).getPrice();
                }
            }
        }
        Order order = new Order();
        order.setUserId(user.getUserid());
        order.setDeskId(Integer.parseInt(deskid));
        order.setCustom(custom);
        order.setTel(tel);
        order.setPrice(priceAll);
        order.setOrderdate(time);
        boolean flag = orderDao.insertOrder(order);
        deskDao.updateDeskStateById(Integer.parseInt(deskid));
        int orderId = orderDao.getOrderId(Integer.parseInt(deskid));
        for (int i = 0; i <ids.length; i++) {
            orderDao.insertOrderFood(orderId, Integer.parseInt(ids[i]));
        }
        String pageNum = request.getParameter("pageNum");
        request.getSession().setAttribute("pageold", pageNum);
        response.sendRedirect("/deskServlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deskid = request.getParameter("deskId");
//        List<Food> foods = foodDao.getFood();
//        request.getSession().setAttribute("Allfoods", foods);
        request.setAttribute("desk", deskid);
        System.out.println(deskid);
        request.getRequestDispatcher("/WEB-INF/orderfood.jsp").forward(request, response);
    }
}
