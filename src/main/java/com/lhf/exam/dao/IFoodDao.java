package com.lhf.exam.dao;

import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.FoodSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFoodDao {
    List<FoodSet> getFoodSet(@Param("start") int start,@Param("length") int length);
    List<FoodSet> getFoodSetAll();
    List<Food> getFoodAll();
    List<Food> getFood(@Param("start") int start,@Param("length") int length);
    Food getFoodById(int foodId);
    boolean updateFoodSet(@Param("oldset")String oldset,@Param("newset") String newset);
    boolean insertFoodSet(String name);
    boolean insertFood(Food food);
    boolean updateFood(Food food);
    boolean deleteFood(int foodId);
    boolean deleteFoodByFoodSetId(int foodSetId);
    boolean deleteFoodSet(int foodSetId);
    int getCount();
    int getFoodSetCount();

}
