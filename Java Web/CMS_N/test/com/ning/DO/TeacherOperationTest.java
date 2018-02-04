package com.ning.DO;

import com.ning.DAO.TeacherEntity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/11.
 */
public class TeacherOperationTest {
    TeacherOperation to = new TeacherOperation();
    TeacherEntity t = new TeacherEntity();

    @Test
    public void selectById() throws Exception {
        System.out.println(to.selectById(2).toJSON());
    }

    @Test
    public void update() {
        t = to.selectById(2);
        System.out.println(t.toJSON());
        t.setCourse("Chinese");
        to.update(t);
    }

}