package com.lhf.exam.servlet;

import com.lhf.exam.service.DeskService;
import com.lhf.exam.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "DeleteDeskServlet",urlPatterns = "/DeleteDesk")
public class DeleteDeskServlet extends HttpServlet {
    DeskService service = ApplicationContextHolder.context.getBean(DeskService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pageNum = request.getParameter("pageNum");
        boolean flag = service.deleteDesk(Integer.parseInt(request.getParameter("deskId")));
        request.getSession().setAttribute("pageold", pageNum);
        request.getRequestDispatcher("/deskServlet").forward(request,response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
