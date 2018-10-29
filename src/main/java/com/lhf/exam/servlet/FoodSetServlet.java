package com.lhf.exam.servlet;

import com.lhf.exam.dao.impl.FoodDaoImpl;
import com.lhf.exam.pojo.FoodSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "FoodSetServlet", urlPatterns = "/FoodSetServlet")
public class FoodSetServlet extends HttpServlet {
    FoodDaoImpl foodDao = new FoodDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String oldset = request.getParameter("oldset");
        String newset = request.getParameter("newset");
        boolean flag = foodDao.updateFoodSet(oldset,newset);
        if (!flag){
            response.sendRedirect("/FoodSetServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = foodDao.getFoodSetCount();
        int pageNum = 2;
        int total = count/pageNum;
        if(count%pageNum!=0){
            total++;
        }
        String pageNumber = null;
        System.out.println(request.getRequestURI());
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
        List<FoodSet> foodSets = foodDao.getFoodSet(start,pageNum);
        request.getSession().setAttribute("foodSets", foodSets);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/cuisineList.jsp").forward(request,response);
    }
}
