package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import com.ning.factory.IGson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassroomOperationTest implements IGson {
    ClassroomOperation co = new ClassroomOperation();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        ClassroomEntity t = new ClassroomEntity();
        t.setId(7);
        t.setCapacity(50);
        t.setNumber("A112");
        t.setExtra("test room");
        System.out.println(co.insert(t));
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        ClassroomEntity classroomEntity = co.selectById(2);
        System.out.println(classroomEntity.toJSON());
    }

    @Test
    public void selectAll() throws Exception {
        System.out.println(gson.toJson(co.selectAll()));
    }

    @Test
    public void selectHireDayById() throws Exception {
    }

}