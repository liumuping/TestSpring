package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;
import com.lhf.exam.service.FoodService;
import com.lhf.exam.util.ApplicationContextHolder;
import com.lhf.exam.util.MybatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "DeleteFoodServlet", urlPatterns = "/DeleteFoodServlet")
public class DeleteFoodServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodService service = ApplicationContextHolder.context.getBean(FoodService.class);
        request.setCharacterEncoding("utf-8");
        System.out.println("delete");
        String pageNum = request.getParameter("pageNum");
        System.out.println(pageNum);
//        boolean flag = service.deleteFood(Integer.parseInt(request.getParameter("foodId")));
        request.getSession().setAttribute("pageold", pageNum);
        response.sendRedirect("/FoodServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
