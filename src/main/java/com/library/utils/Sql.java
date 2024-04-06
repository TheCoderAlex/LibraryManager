package com.library.utils;

import com.library.mapper.LibraryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Sql {
    private Sql() {}
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static SqlSession getSession(boolean b) {
        return sqlSessionFactory.openSession(b);
    }
    public static LibraryMapper getMapper(boolean b) {
        return getSession(b).getMapper(LibraryMapper.class);
    }
}
