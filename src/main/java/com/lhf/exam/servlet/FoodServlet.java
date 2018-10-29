package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;
import com.lhf.exam.pojo.Food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "FoodServlet", urlPatterns = "/FoodServlet")
public class FoodServlet extends HttpServlet {
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        int foodsetId = Integer.parseInt(request.getParameter("foodsetid"));
        String foodname = request.getParameter("foodname");
        double price = Double.parseDouble(request.getParameter("price"));
        Food food = new Food();
        food.setName(foodname);
        food.setFoodsetId(foodsetId);
        food.setPrice(price);
        boolean flag = false;
        if (method.equals("1")){
            flag= foodDao.insertFood(food);
        }else if (method.equals("2")){
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            food.setId(foodId);
            flag= foodDao.updateFood(food);
            System.out.println(12);
        }
        if (flag){
            response.sendRedirect("/FoodServlet");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = foodDao.getCount();
        int pageNum = 3;
        int total = count/pageNum;
        if(count%pageNum!=0){
            total++;
        }
        String pageNumber = null;
        String method = request.getParameter("method");
        if (request.getParameter("pageNumber")!=null || "1".equals(method)){
            pageNumber = request.getParameter("pageNumber");
        }else {
            pageNumber = (String) request.getSession().getAttribute("pageold");
        }
        //获取当前页码页面的数据

        if(pageNumber==null||pageNumber.equals("")){
            pageNumber="1";
        }
        int start=0;//默认pageNumber=0+1开始检索
        //根据当前页码进行检索
        int number = Integer.parseInt(pageNumber);
        start=(number-1)*pageNum;
        List<Food> foods = foodDao.getFood(start,pageNum);
        request.getSession().setAttribute("foods", foods);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/foodList.jsp").forward(request,response);
    }
}
