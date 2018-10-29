package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "InsertFoodSetServlet",urlPatterns = "/InsertFoodSetServlet")
public class InsertFoodSetServlet extends HttpServlet {
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String foodsetname = request.getParameter("foodsetname");
        boolean flag = foodDao.insertFoodSet(foodsetname);
        if (flag){
            response.sendRedirect("/FoodSetServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
