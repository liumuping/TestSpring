package com.lhf.exam.dao.impl;

import com.lhf.exam.dao.IUserDao;
import com.lhf.exam.pojo.AccessRight;
import com.lhf.exam.pojo.Role;
import com.lhf.exam.pojo.User;
import com.lhf.exam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public User getUserByNameAndPwd(String username, String password) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUserid(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return user;
    }

    @Override
    public List<Role> getRoleByUserId(int userId) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role WHERE roleId IN (SELECT roleId FROM usertorole WHERE userId = ?)";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("roleId"));
                role.setName(resultSet.getString("rolename"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return roles;
    }

    @Override
    public List<AccessRight> getRightByRoleId(int roleId) {
        List<AccessRight> accessRights = new ArrayList<>();
        String sql = "SELECT * FROM access WHERE roleId = ?";
        conn = JDBCUtils.getCon();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, roleId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AccessRight accessRight = new AccessRight();
                accessRight.setRightId(resultSet.getInt("rightId"));
                accessRight.setRightname(resultSet.getString("rightname"));
                accessRight.setRighturi(resultSet.getString("righturi"));
                accessRights.add(accessRight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }
        return accessRights;
    }
}
