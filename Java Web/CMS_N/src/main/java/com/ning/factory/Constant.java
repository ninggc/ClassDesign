package com.ning.factory;

import com.google.gson.Gson;
import com.ning.DO.ClassroomOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ning on 2017/7/4.
 */
public class Constant {
    public static final Gson gson = new Gson();
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

//    public static String test() {
//        return new ClassroomOperation().selectById(1).toJSON();
//    }

    public ArrayList list = new ArrayList();

    public ArrayList getList() {
        list.add(123);
        list.add(456);
        list.add(456);
        list.add(456);
        return list;
    }

}
