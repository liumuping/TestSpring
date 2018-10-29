package com.lhf.exam.servlet;

import com.lhf.exam.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "index",urlPatterns = "/index")
public class InderxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user==null){
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response );
        }
        request.getRequestDispatcher("/LoginServlet").forward(request,response);

    }
}
