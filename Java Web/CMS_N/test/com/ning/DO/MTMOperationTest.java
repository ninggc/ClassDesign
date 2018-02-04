package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/11.
 */
public class MTMOperationTest {
    Operation<ClassroomEntity> o;
    @Before
    public void setUp() throws Exception {
        o = new Operation<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        o.selectById(1, new DoInDO<Integer, ClassroomEntity>() {
            @Override
            public ClassroomEntity dosomething(Session session, Integer integer) throws Exception {
                return null;
            }
        });
    }

    @Test
    public void selectBy() throws Exception {
    }

    @Test
    public void selectAll() throws Exception {
    }

}