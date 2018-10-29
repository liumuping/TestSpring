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
import java.util.List;

//@WebServlet(name = "DeskServlet",urlPatterns = "/deskServlet")
//public class DeskServlet extends HttpServlet {
//
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DeskService service = ApplicationContextHolder.context.getBean(DeskService.class);
//        request.setCharacterEncoding("utf-8");
//        Desk desk = new Desk();
//        desk.setName(request.getParameter("dname"));
//        desk.setContent(Integer.parseInt(request.getParameter("dcontent")));
//        service.insertDesk(desk);
//        response.sendRedirect("/deskServlet");
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DeskService service = ApplicationContextHolder.context.getBean(DeskService.class);
//        int count = service.getCount();
//        int pageNum = 4;
//        int total = count/pageNum;
//        if(count%pageNum!=0){
//            total++;
//        }
//
//        //获取当前页码页面的数据
//        String pageNumber = null;
//        String method = request.getParameter("method");
//        if (request.getParameter("pageNumber")!=null || "1".equals(method)){
//            pageNumber = request.getParameter("pageNumber");
//        }else {
//            pageNumber = (String) request.getSession().getAttribute("pageold");
//        }
//        if(pageNumber==null||pageNumber.equals("")){
//            pageNumber="1";
//        }
//        int start=0;//默认pageNumber=0+1开始检索
//        //根据当前页码进行检索
//        int number = Integer.parseInt(pageNumber);
//        start=(number-1)*pageNum;
//        List<Desk> desks =  service.getDesks(start,pageNum);
//        request.getSession().setAttribute("pageNumber", number);
//        request.getSession().setAttribute("total", total);
//        request.getSession().setAttribute("desks", desks);
//        request.getRequestDispatcher("/WEB-INF/boardList.jsp").forward(request, response);
//
//
//    }
//}
