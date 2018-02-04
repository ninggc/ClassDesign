package com.ning.factory;

import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

//如果这个出错了就证明Hibernate配置有误
public class MySessionFactoryTest {
    @Test
    public void getSession() throws Exception {
        Session session = MySessionFactory.getSession();
    }

}