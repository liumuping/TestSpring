package com.lhf.exam.util;

import java.sql.*;

public class JDBCUtils {

    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/restaurant?characterEncoding=utf8&useSSL=true";
    private static String username="root";
    private static String password="123456";

    /*
    数据库连接方法
     */
    static {
        //加载配置信息
        loading();

    }

    private static void loading(){
        try {
//            InputStream in = JDBCUtils.class.getClassLoader()
//                    .getResourceAsStream("jdbc.properties");
//            // 创建属性集对象
//            // 先创建输入流来获取文件内容
//            //两种方法获取配置文件输入流信息
//            Properties properties = new Properties();// 创建一个空属性列表实例,Properties 可保存在流中或从流中加载
//            properties.load(in);//从输入流中读取属性列表（键和元素对）
//            username = properties.getProperty("username");//配置信息
//            password = properties.getProperty("password");
//            driver = properties.getProperty("driver");
//            url = properties.getProperty("url");
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException("操作配置属性文件异常", e);
        }
    }

    public static Connection getCon() {

        Connection conn = null;

        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    /*
    关闭连接
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
