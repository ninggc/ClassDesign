package com.ning.factory;

import com.ning.DAO.ClassroomEntity;
import com.ning.DO.ClassroomOperation;
import com.ning.factory.Constant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/4.
 */
public class ConstantTest {

    @Test
    public void testGson() {
        ClassroomEntity c = new ClassroomEntity();
        c = new ClassroomOperation().selectById(1);
        String json = c.toJSON();
        System.out.println(json);
        ClassroomEntity classroomEntity = Constant.gson.fromJson(json, ClassroomEntity.class);
        System.out.println(classroomEntity.toJSON());
    }

//    @Test
//    public void test() {
//        System.out.println(Constant.test());
//    }

}