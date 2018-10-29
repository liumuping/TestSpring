package com.lhf.exam.servlet;

//import com.lhf.exam.pojo.AccessRight;
//import com.lhf.exam.pojo.Role;
//import com.lhf.exam.pojo.User;
//import com.lhf.exam.service.UserService;
//import com.lhf.exam.util.ApplicationContextHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.annotation.WebServlet;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//@Slf4j
//@Controller
////Controller("bean9527")
//@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
//public class LoginServlet extends javax.servlet.http.HttpServlet {
//    public LoginServlet(){
//        log.info("Invoke DeskServlet constructor method...{}", this);
//    };
//    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
////        UserService userService = new UserService();
//        UserService userService = ApplicationContextHolder.context.getBean(UserService .class);
//                User user = userService.login(username, password);
//        if (user.getUserid() > 0) {
//            request.getSession().setAttribute("user", user);
//            List<AccessRight> rights = new ArrayList<>();
//            for (Role role : user.getRoles()) {
//                rights.addAll(role.getRights());
//            }
//            request.getSession().setAttribute("rights", rights);
//            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
//        }
//    }
//
//    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user==null){
//            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response );
//        }else {
//            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
//        }
//    }
//}
