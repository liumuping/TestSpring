package com.lhf.exam.service;

import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.FoodSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFoodService {
    List<FoodSet> getFoodSet(int start, int length);
    List<FoodSet> getFoodSetAll();
    List<Food> getFoodAll();
    List<Food> getFood(int start, int length);
    Food getFoodById(int foodId);
    boolean updateFoodSet( String oldset,String newset);
    boolean insertFoodSet(String name);
    boolean insertFood(Food food);
    boolean updateFood(Food food);
    boolean deleteFood(int foodId);
    boolean deleteFoodByFoodSetId(int foodSetId);
    boolean deleteFoodSet(int foodSetId);
    int getCount();
    int getFoodSetCount();

}
