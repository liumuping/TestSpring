package com.lhf.exam.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSession session;
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "configuration.xml";
        InputStream inputStream = null;
        session = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            session = sqlSessionFactory.openSession();
            System.out.println("xml is null");
        }
    }

    public static SqlSession creatSqlSession() {
        session = sqlSessionFactory.openSession();
        return session;
    }

    public static void closeSqlSession(SqlSession sqlSession) {
        sqlSession.close();
    }
}
