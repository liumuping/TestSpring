package com.lhf.exam.service;

import com.lhf.exam.dao.IFoodDao;
import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.FoodSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Scope("prototype")
public class FoodService implements IFoodService {
    @Autowired
    private IFoodDao iFoodDao;
    @Override
    public List<FoodSet> getFoodSet(int start, int length) {
        List<FoodSet> foodsets = new ArrayList<>();
        foodsets = iFoodDao.getFoodSet(start,length);
        return foodsets;
    }

    @Override
    public List<FoodSet> getFoodSetAll() {
        List<FoodSet> foodsets = new ArrayList<>();
        foodsets = iFoodDao.getFoodSetAll();
        return foodsets;
    }

    @Override
    public List<Food> getFoodAll() {
        List<Food> foods = new ArrayList<>();
        foods = iFoodDao.getFoodAll();
        return foods;
    }

    @Override
    public List<Food> getFood(int start, int length) {
        List<Food> foods = new ArrayList<>();
        foods = iFoodDao.getFood(start,length);
        return foods;
    }

    @Override
    public Food getFoodById(int foodId) {
        Food food = new Food();
        food = iFoodDao.getFoodById(foodId);
        return food;
    }

    @Override
    public boolean updateFoodSet(String oldset, String newset) {
        Boolean flag = false;
        flag = iFoodDao.updateFoodSet(oldset,newset);
        return flag;
    }

    @Override
    public boolean insertFoodSet(String name) {
        Boolean flag = false;
        flag = iFoodDao.insertFoodSet(name);
        return flag;
    }

    @Override
    public boolean insertFood(Food food) {
        Boolean flag = false;
        flag = iFoodDao.insertFood(food);
        return flag;
    }

    @Override
    public boolean updateFood(Food food) {
        Boolean flag = false;
        flag = iFoodDao.updateFood(food);
        return flag;
    }

    @Override
    public boolean deleteFood(int foodId) {
        Boolean flag = false;
        flag = iFoodDao.deleteFood(foodId);
        return flag;
    }

    @Override
    public boolean deleteFoodByFoodSetId(int foodSetId) {
        Boolean flag = false;
        flag = iFoodDao.deleteFoodByFoodSetId(foodSetId);
        return flag;
    }

    @Override
    public boolean deleteFoodSet(int foodSetId) {
        Boolean flag = false;
        flag = iFoodDao.deleteFoodSet(foodSetId);
        return flag;
    }

    @Override
    public int getCount() {
        int flag = 0;
        flag = iFoodDao.getCount();
        return flag;
    }

    @Override
    public int getFoodSetCount() {
        int flag = 0;
        flag = iFoodDao.getFoodSetCount();
        return flag;
    }
}
