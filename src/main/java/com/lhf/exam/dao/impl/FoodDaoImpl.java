package com.lhf.exam.dao.impl;

import com.lhf.exam.dao.IFoodDao;
import com.lhf.exam.pojo.Food;
import com.lhf.exam.pojo.FoodSet;
import com.lhf.exam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements IFoodDao {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<FoodSet> getFoodSet(int start,int length) {
        List<FoodSet> foodSets = new ArrayList<>();
        String sql = "SELECT  * FROM foodset WHERE isdeleted = 0 LIMIT ?,?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2,length);
            resultSet = preparedStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                FoodSet foodSet = new FoodSet();
                foodSet.setFoodsetId(resultSet.getInt("foodsetId"));
                foodSet.setFoodsetname(resultSet.getString("foodsetname"));
                foodSets.add(foodSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return foodSets;
    }

    @Override
    public List<FoodSet> getFoodSetAll() {
        List<FoodSet> foodSets = new ArrayList<>();
        String sql = "SELECT  * FROM foodset WHERE isdeleted = 0";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                FoodSet foodSet = new FoodSet();
                foodSet.setFoodsetId(resultSet.getInt("foodsetId"));
                foodSet.setFoodsetname(resultSet.getString("foodsetname"));
                foodSets.add(foodSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return foodSets;
    }

    @Override
    public List<Food> getFoodAll() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT food.foodId,food.foodname,foodset.foodsetname,food.foodprice FROM food LEFT JOIN foodset ON food.foodsetId = foodset.foodsetId WHERE food.isdeleted = 0";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();
                food.setId(resultSet.getInt("foodId"));
                food.setName(resultSet.getString("foodname"));
                food.setFoodset(resultSet.getString("foodsetname"));
                food.setPrice(resultSet.getDouble("foodprice"));
                food.setVipprice(resultSet.getDouble("foodprice") * 0.8);
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return foods;
    }

    @Override
    public List<Food> getFood(int start,int length) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT food.foodId,food.foodname,foodset.foodsetname,food.foodprice FROM food LEFT JOIN foodset ON food.foodsetId = foodset.foodsetId WHERE food.isdeleted = 0 LIMIT ?,?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2,length);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();
                food.setId(resultSet.getInt("foodId"));
                food.setName(resultSet.getString("foodname"));
                food.setFoodset(resultSet.getString("foodsetname"));
                food.setPrice(resultSet.getDouble("foodprice"));
                food.setVipprice(resultSet.getDouble("foodprice") * 0.8);
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return foods;
    }

    @Override
    public Food getFoodById(int foodId) {
        Food food = new Food();
        String sql = "SELECT * FROM food WHERE foodId = ?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, foodId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                food.setId(resultSet.getInt("foodId"));
                food.setName(resultSet.getString("foodname"));
                food.setFoodsetId(resultSet.getInt("foodsetId"));
                food.setPrice(resultSet.getDouble("foodprice"));
                food.setVipprice(resultSet.getDouble("foodprice") * 0.8);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return food;
    }

    @Override
    public boolean updateFoodSet(String oldset, String newset) {
        boolean flag = false;
        String sql = "UPDATE foodset SET foodsetname = ? WHERE foodsetname = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newset);
            preparedStatement.setString(2, oldset);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return flag;
    }

    @Override
    public boolean insertFoodSet(String name) {
        Boolean flag;
        String sql = "INSERT INTO foodset(foodsetname) VALUES(?)";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return flag;
    }

    @Override
    public boolean insertFood(Food food) {
        Boolean flag;
        String sql = "INSERT INTO food(foodname,foodprice,foodsetId) VALUES(?,?,?)";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setDouble(2, food.getPrice());
            preparedStatement.setInt(3, food.getFoodsetId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return flag;
    }

    @Override
    public boolean updateFood(Food food) {
        Boolean flag;
        String sql = "UPDATE food SET foodname=?,foodprice=?,foodsetId=? WHERE foodId=? ";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setDouble(2, food.getPrice());
            preparedStatement.setInt(3, food.getFoodsetId());
            preparedStatement.setInt(4,food.getId() );
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return flag;
    }

    @Override
    public boolean deleteFood(int foodId) {
        boolean flag = false;
        String sql = "UPDATE food SET isdeleted = 1 WHERE foodId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, foodId);
            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,conn);
        }
        return flag;
    }

    @Override
    public boolean deleteFoodByFoodSetId(int foodSetId) {
        boolean flag = false;
        String sql = "UPDATE food SET isdeleted = 1 WHERE foodsetId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, foodSetId);
            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,conn);
        }
        return flag;
    }

    @Override
    public boolean deleteFoodSet(int foodSetId) {
        boolean flag = false;
        String sql = "UPDATE foodset SET isdeleted = 1 WHERE foodsetId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, foodSetId);
            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,conn);
        }
        return flag;
    }

    @Override
    public int getCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM food WHERE isdeleted = 0";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return count;
    }

    @Override
    public int getFoodSetCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM foodset WHERE isdeleted = 0";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return count;
    }
}
