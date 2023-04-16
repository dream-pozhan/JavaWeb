package com.itheima;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;


/*
* mybatis代理开发
* */
public class MybatisDemo2 {
    public static void main(String[] args) throws Exception {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 执行sql
        //List<User> users = sqlSession.selectList("test.selectAll");
        //获取接口代理对象
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = userMapper.selectAll();
//        System.out.println(users);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        //4. 释放资源
        sqlSession.close();
    }
}
