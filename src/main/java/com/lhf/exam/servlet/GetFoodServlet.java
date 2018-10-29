package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;
import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.FoodSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "GetFoodServlet", urlPatterns = "/GetFoodServlet")
public class GetFoodServlet extends HttpServlet {
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        Food food = foodDao.getFoodById(foodId);
        List<FoodSet> foodSets = foodDao.getFoodSetAll();
        request.setAttribute("allfoodSets", foodSets);
        request.setAttribute("food",food );
        request.getRequestDispatcher("/WEB-INF/updateFood.jsp").forward(request,response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }
}
