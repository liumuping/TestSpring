package com.lhf.exam.dao.impl;

import com.lhf.exam.dao.IOrderDao;
import com.lhf.exam.pojo.Order;
import com.lhf.exam.pojo.OrderFood;
import com.lhf.exam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public List<Order> getOrder(int start, int length) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orderlist.orderId,desk.deskname,orderlist.ordertime,orderlist.orderprice,orderlist.orderstate,orderlist.custom,orderlist.tel FROM orderlist LEFT JOIN desk ON orderlist.deskId = desk.deskId WHERE orderlist.isdeleted = 0 LIMIT ?,?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2,length);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("orderId"));
                order.setDeskname(resultSet.getString("deskname"));
                order.setPrice(resultSet.getDouble("orderprice"));
                DecimalFormat df=new DecimalFormat(".##");
                double d=Double.valueOf(df.format(order.getPrice()*0.8));
                order.setVipprice(d);
                order.setOrderdate(resultSet.getString("ordertime"));
                order.setState(resultSet.getInt("orderstate"));
                order.setCustom(resultSet.getString("custom"));
                order.setTel(resultSet.getString("tel"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return orders;
    }

    @Override
    public boolean insertOrder(Order order) {
        Boolean flag;
        String sql = "INSERT INTO orderlist(userId,deskId,orderprice,ordertime,tel,custom) VALUES(?,?,?,?,?,?)";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getDeskId());
            preparedStatement.setDouble(3, order.getPrice());
            preparedStatement.setString(4, order.getOrderdate());
            preparedStatement.setString(5, order.getTel());
            preparedStatement.setString(6, order.getCustom());
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
    public int getOrderId(int deskId) {
        int orderId = 0;
        String sql = "SELECT orderId FROM orderlist WHERE orderstate = 0";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               orderId = resultSet.getInt("orderId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return orderId;
    }

    @Override
    public boolean insertOrderFood(int orderId, int foodId) {
        Boolean flag;
        String sql = "INSERT INTO order_food(orderId,foodId) VALUES(?,?)";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, foodId);
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
    public List<OrderFood> getOrderFood(int orderId) {
        List<OrderFood> orderFoods = new ArrayList<>();
        String sql = "select food.foodname,food.foodprice,COUNT(foodname) FROM food JOIN order_food ON food.foodId= order_food.foodId WHERE orderId = ? GROUP BY foodname";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,orderId );
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderFood orderFood = new OrderFood();
                orderFood.setFoodname(resultSet.getString("foodname"));
                orderFood.setPrice(resultSet.getDouble("foodprice"));
                orderFood.setCount(resultSet.getInt("COUNT(foodname)"));
                orderFoods.add(orderFood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return orderFoods;
    }

    @Override
    public boolean finishOrder(int orderId) {
        boolean flag = false;
        String sql = "UPDATE orderlist SET orderstate = 1 WHERE orderId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
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
    public int getCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM orderlist WHERE isdeleted = 0";
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
