package util;

import dao.EmpMapper;
import entity.Emp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class Sqlsession{

    public static SqlSession getSqlSession(boolean autocommit){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build
                (Sqlsession.class.getClassLoader().getResourceAsStream("mybatis.xml"));
        SqlSession sqlSession =sqlSessionFactory.openSession(autocommit);
        return sqlSession;
    }

}