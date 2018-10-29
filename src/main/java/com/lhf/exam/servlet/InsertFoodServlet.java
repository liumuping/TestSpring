package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "InsertFoodServlet",urlPatterns = "/InsertFoodServlet")
public class InsertFoodServlet extends HttpServlet {
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/saveFood.jsp").forward(request,response );
    }
}
