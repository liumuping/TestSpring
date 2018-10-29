package com.lhf.exam.controller;

import com.lhf.exam.pojo.Food;
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
public class FoodController {
    @Autowired
    @Setter
    private FoodService foodService;

    @RequestMapping(path = "/DeleteFoodServlet", method = RequestMethod.GET)
    public ModelAndView deleteFood(HttpServletRequest request) {
        String pageNum = request.getParameter("pageNum");
        request.getSession().setAttribute("pageold", pageNum);
        ModelAndView view = new ModelAndView();
        view.setViewName("foodList");
        return view;
    }

    @RequestMapping(path = "/GetFoodServlet", method = RequestMethod.GET)
    public ModelAndView getUpdateFood(HttpServletRequest request) {
        List<FoodSet> foodSets = foodService.getFoodSetAll();
        request.setAttribute("foodSets", foodSets);
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        Food food = foodService.getFoodById(foodId);
        request.setAttribute("food",food );
        ModelAndView view = new ModelAndView();
        view.setViewName("updateFood");
        return view;
    }

    @RequestMapping(path = "/UpdateFoodServlet", method = RequestMethod.POST)
    public ModelAndView updateFood(HttpServletRequest request) {
        String method = request.getParameter("method");
        List<FoodSet> foodSets = foodService.getFoodSetAll();
        request.setAttribute("foodSets", foodSets);
        String foodname = request.getParameter("foodname");
        double price = Double.parseDouble(request.getParameter("price"));
        Food food = new Food();
        food.setName(foodname);
        food.setPrice(price);
        boolean flag = false;
        flag = foodService.updateFood(food);
        ModelAndView view = new ModelAndView();
        view.setViewName("foodList");
        return view;

    }

    @RequestMapping(path = "/InsertFoodServlet", method = RequestMethod.GET)
    public ModelAndView insertFood(HttpServletRequest request) {
        String foodname = request.getParameter("foodname");
        double price = Double.parseDouble(request.getParameter("price"));
        Food food = new Food();
        food.setName(foodname);
        food.setPrice(price);
        boolean flag = false;
        flag = foodService.insertFood(food);
        ModelAndView view = new ModelAndView();
        view.setViewName("foodList");
        return view;

    }

    @RequestMapping(path = "/InsertFood", method = RequestMethod.GET)
    public ModelAndView getinsertFood(HttpServletRequest request) {
        List<FoodSet> foodSets = foodService.getFoodSetAll();
        request.setAttribute("foodSets", foodSets);
        ModelAndView view = new ModelAndView();
        view.setViewName("saveFood");
        return view;

    }

    @RequestMapping(path = "/FoodServlet", method = RequestMethod.GET)
    public ModelAndView getFoodSet(HttpServletRequest request) {
        int count = foodService.getCount();
        int pageNum = 3;
        int total = count / pageNum;
        if (count % pageNum != 0) {
            total++;
        }
        String pageNumber = null;
        String method = request.getParameter("method");
        if (request.getParameter("pageNumber") != null || "1".equals(method)) {
            pageNumber = request.getParameter("pageNumber");
        } else {
            pageNumber = (String) request.getSession().getAttribute("pageold");
        }
        //获取当前页码页面的数据

        if (pageNumber == null || pageNumber.equals("")) {
            pageNumber = "1";
        }
        int start = 0;//默认pageNumber=0+1开始检索
        //根据当前页码进行检索
        int number = Integer.parseInt(pageNumber);
        start = (number - 1) * pageNum;
        List<Food> foods = foodService.getFood(start, pageNum);
        request.getSession().setAttribute("foods", foods);
        request.getSession().setAttribute("pageNumber", number);
        request.getSession().setAttribute("total", total);
        ModelAndView view = new ModelAndView();
        view.setViewName("foodList");
        return view;
    }
}
