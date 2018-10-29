package com.lhf.exam.servlet;

import com.lhf.exam.pojo.Desk;
import com.lhf.exam.service.DeskService;
import com.lhf.exam.util.ApplicationContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "UpdateDeskServlet",urlPatterns = "/UpdateDeskServlet")
public class UpdateDeskServlet extends HttpServlet {
    DeskService service = ApplicationContextHolder.context.getBean(DeskService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String olddesk = request.getParameter("oldname");
        Desk desk = new Desk();
        desk.setName(request.getParameter("dname"));
        desk.setContent(Integer.parseInt(request.getParameter("dcontent")));
        boolean flag = service.updateDesk(olddesk,desk);
        if (flag){
            System.out.println(1);
        }else {
            System.out.println(2);
        }
        response.sendRedirect("/deskServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String olddeskname = request.getParameter("olddeskname");
        String oldcontent = request.getParameter("oldcontent");
        request.setAttribute("oldcontent", oldcontent);
        request.setAttribute("olddeskname",olddeskname);
        String pageNum = request.getParameter("pageNum");
        request.getSession().setAttribute("pageold", pageNum);
        request.getRequestDispatcher("/WEB-INF/updateBoard.jsp").forward(request,response);
    }
}
