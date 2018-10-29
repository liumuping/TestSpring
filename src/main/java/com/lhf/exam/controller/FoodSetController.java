package com.lhf.exam.controller;

import com.lhf.exam.pojo.Desk;
import com.lhf.exam.pojo.FoodSet;
import com.lhf.exam.service.FoodService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class FoodSetController {
    @Autowired
    @Setter
    private FoodService foodService;

    @RequestMapping(path = "/DeleteFoodSetServlet", method = RequestMethod.GET)
    public ModelAndView deleteFoodSet(HttpServletRequest request){
        String pageNum = request.getParameter("pageNum");
        int foodsetId = Integer.parseInt(request.getParameter("foodsetId"));
        request.getSession().setAttribute("pageold", pageNum);
        foodService.deleteFoodSet(foodsetId);
        foodService.deleteFoodByFoodSetId(foodsetId);
        ModelAndView view = new ModelAndView();
        view.setViewName("cuisineList");
        return view;
    }
    @RequestMapping(path = "/UpdateSetServlet", method = RequestMethod.GET)
    public ModelAndView getUpdateFoodSet(HttpServletRequest request){
        String oldset = request.getParameter("oldset");
        request.setAttribute("oldset",oldset);
        ModelAndView view = new ModelAndView();
        view.setViewName("updateCuisine");
        return view;
    }
    @RequestMapping(path = "/UpdateFoodSetServlet", method = RequestMethod.POST)
    public ModelAndView updateFoodSet(HttpServletRequest request){
        String oldset = request.getParameter("oldset");
        String newset = request.getParameter("newset");
        boolean flag = foodService.updateFoodSet(oldset,newset);
            ModelAndView view = new ModelAndView();
            view.setViewName("cuisineList");
            return view;

    }
    @RequestMapping(path = "/InsertFoodSetServlet", method = RequestMethod.POST)
    public ModelAndView insertFoodSet(HttpServletRequest request){
        String foodsetname = request.getParameter("foodsetname");
        boolean flag = foodService.insertFoodSet(foodsetname);
        ModelAndView view = new ModelAndView();
        view.setViewName("cuisineList");
        return view;

    }
    @RequestMapping(path = "/FoodSetServlet", method = RequestMethod.GET)
    public ModelAndView getFoodSet(HttpServletRequest request){
        int count = foodService.getFoodSetCount();
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
        List<FoodSet> foodSets = foodService.getFoodSet(start,pageNum);
        request.getSession().setAttribute("foodSets", foodSets);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        ModelAndView view = new ModelAndView();
        view.setViewName("cuisineList");
        return view;
    }
}
