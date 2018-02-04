package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import com.ning.DAO.DayEntity;
import com.ning.DAO.HireEntity;
import com.ning.DAO.TeacherEntity;
import com.ning.factory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/10.
 */
public class OperationTest {
    TeacherOperation to = new TeacherOperation();
    ClassroomOperation co = new ClassroomOperation();

    @Test
    public void test() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();


            ClassroomEntity c = new ClassroomEntity();
            c = co.selectById(2);

            TeacherEntity t = new TeacherEntity();
            t = to.selectById(2);

//            Set<ClassroomEntity> set = new HashSet<>();
//            set.add(c);
//            t.setClassrooms(set);

//            session.save(t);
            System.out.println(t.toJSON());

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
    }

}