package com.lhf.exam.controller;

import com.lhf.exam.pojo.Desk;
import com.lhf.exam.pojo.Order;
import com.lhf.exam.service.DeskService;
import com.sun.tools.javac.util.MandatoryWarningHandler;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeskController {
    @Autowired
    @Setter
    private DeskService deskService;
    @RequestMapping(path = "/DeleteDesk", method = RequestMethod.GET)
    public ModelAndView deleteDesk(HttpServletRequest request) {
        String pageNum = request.getParameter("pageNum");
        boolean flag = deskService.deleteDesk(Integer.parseInt(request.getParameter("deskId")));
        request.getSession().setAttribute("pageold", pageNum);
        ModelAndView view = new ModelAndView();
        view.setViewName("boardList");
        return view;

    }
    @RequestMapping(path = "/InsertDesk", method = RequestMethod.POST)
    public ModelAndView insertDesk(HttpServletRequest request) {
        Desk desk = new Desk();
        desk.setName(request.getParameter("dname"));
        desk.setContent(Integer.parseInt(request.getParameter("dcontent")));
        deskService.insertDesk(desk);
        ModelAndView view = new ModelAndView();
        view.setViewName("boardList");
        return view;
    }
    @RequestMapping(path = "/UpdateDesk", method = RequestMethod.POST)
    public ModelAndView updateDesk(HttpServletRequest request) {
        String olddesk = request.getParameter("oldname");
        Desk desk = new Desk();
        desk.setName(request.getParameter("dname"));
        desk.setContent(Integer.parseInt(request.getParameter("dcontent")));
        boolean flag = deskService.updateDesk(olddesk,desk);
        ModelAndView view = new ModelAndView();
        view.setViewName("boardList");
//        updateBoard.jsp
        return view;
    }
    @RequestMapping(path = "/UpdateDeskServlet", method = RequestMethod.GET)
    public ModelAndView getupdateDesk(HttpServletRequest request) {
        String olddeskname = request.getParameter("olddeskname");
        String oldcontent = request.getParameter("oldcontent");
        request.setAttribute("oldcontent", oldcontent);
        request.setAttribute("olddeskname",olddeskname);
        String pageNum = request.getParameter("pageNum");
        request.getSession().setAttribute("pageold", pageNum);
        ModelAndView view = new ModelAndView();
        view.setViewName("updateBoard");
        return view;
    }
    @RequestMapping(path = "/deskServlet", method = RequestMethod.GET)
    public ModelAndView getDesk(HttpServletRequest request){
        int count = deskService.getCount();
        int pageNum = 4;
        int total = count/pageNum;
        if(count%pageNum!=0){
            total++;
        }

        //获取当前页码页面的数据
        String pageNumber = null;
        String method = request.getParameter("method");
        if (request.getParameter("pageNumber")!=null || "1".equals(method)){
            pageNumber = request.getParameter("pageNumber");
        }else {
            pageNumber = (String) request.getSession().getAttribute("pageold");
        }
        if(pageNumber==null||pageNumber.equals("")){
            pageNumber="1";
        }
        int start=0;//默认pageNumber=0+1开始检索
        //根据当前页码进行检索
        int number = Integer.parseInt(pageNumber);
        start=(number-1)*pageNum;
        List<Desk> desks =  deskService.getDesks(start,pageNum);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("desks", desks);
        ModelAndView view = new ModelAndView();
        view.setViewName("boardList");
        return view;
    }

}
