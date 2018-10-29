package com.lhf.exam.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "UpdateSetServlet",urlPatterns = "/UpdateSetServlet")
public class UpdateSetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldset = request.getParameter("oldset");
        request.setAttribute("oldset",oldset);
        request.getRequestDispatcher("/WEB-INF/updateCuisine.jsp").forward(request,response);
    }
}
