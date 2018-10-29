package com.lhf.exam.controller;

import com.lhf.exam.pojo.AccessRight;
import com.lhf.exam.pojo.Role;
import com.lhf.exam.pojo.User;
import com.lhf.exam.service.IUserService;
import com.lhf.exam.service.UserService;
import com.lhf.exam.util.ApplicationContextHolder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    @Setter
    private UserService userService;
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,String username, String password) {
        // 如果登陆成功，到主页；登陆失败，到登陆界面
        User user = userService.login(username, password);
        ModelAndView view = new ModelAndView();
        if (user != null) {
            request.getSession().setAttribute("user", user);
            List<AccessRight> rights = new ArrayList<>();
            for (Role role : user.getRoles()) {
                rights.addAll(role.getRights());
                log.info("@"+rights.toString());
            }
            request.getSession().setAttribute("rights", rights);
            view.setViewName("home");
            return view;
        } else {
            view.setViewName("index");
            view.addObject("errMsg", "登陆失败，请重试！");
            return view;
        }
    }
    @RequestMapping(path = "/Web", method = RequestMethod.GET)
    public ModelAndView getRight(HttpServletRequest request) {
        String name = request.getParameter("name");
        ModelAndView view =new ModelAndView();
        view.setViewName(name);
        return view;
    }
    @RequestMapping(path = "/WebServlet", method = RequestMethod.GET)
    public ModelAndView getTop(HttpServletRequest request) {
        String name = request.getParameter("name");
        ModelAndView view =new ModelAndView();
        view.setViewName(name);
        return view;
    }

}
