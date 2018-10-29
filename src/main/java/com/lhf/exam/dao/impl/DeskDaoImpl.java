package com.lhf.exam.dao.impl;

import com.lhf.exam.dao.IDeskDao;
import com.lhf.exam.pojo.Desk;
import com.lhf.exam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeskDaoImpl implements IDeskDao {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public List<Desk> getDesks(int start, int length) {
        List<Desk> desks = new ArrayList<>();
        String sql = "SELECT * FROM desk WHERE isdeleted = 0 LIMIT ?,?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2,length);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Desk desk = new Desk();
                desk.setId(resultSet.getInt("deskId"));
                desk.setName(resultSet.getString("deskname"));
                desk.setContent(resultSet.getInt("deskcontent"));
                desk.setState(resultSet.getInt("deskstate"));
                desks.add(desk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return desks;
    }

    @Override
    public int insertDesk(Desk desk) {
        Boolean flag;
        String sql = "INSERT INTO desk(deskname,deskcontent) VALUES(?,?)";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, desk.getName());
            preparedStatement.setInt(2,desk.getContent());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;

        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return 0;
    }

    @Override
    public boolean deleteDesk(int deskId) {
        boolean flag = false;
        String sql = "UPDATE desk SET isdeleted = 1 WHERE deskId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, deskId);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 1) {
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
    public boolean updateDeskStateById(int deskId) {
        boolean flag = false;
        String sql = "UPDATE desk SET deskstate = 1 WHERE deskId = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, deskId);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 1) {
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
    public boolean updateDeskStateByName(String deskname) {
        boolean flag = false;
        String sql = "UPDATE desk SET deskstate = 0 WHERE deskname = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, deskname);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 1) {
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
        String sql = "SELECT COUNT(*) FROM desk WHERE isdeleted = 0";
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
    public boolean updateDesk(String deskname, Desk desk) {
        boolean flag = false;
        String sql = "UPDATE desk SET deskname = ?,deskcontent=? WHERE deskname = ?";
        try {
            conn = JDBCUtils.getCon();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, desk.getName());
            preparedStatement.setInt(2, desk.getContent());
            preparedStatement.setString(3, deskname);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,conn);
        }
        return flag;
    }


}
