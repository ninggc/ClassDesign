package com.ning.DO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.DAO.ClassroomEntity;
import com.ning.DAO.HireEntity;
import com.ning.DAO.TeacherEntity;
import com.ning.DAO.TimeEntity;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/11.
 */
public class HireOperationTest {
    HireOperation ho = new HireOperation();
    Gson gson = new Gson();

    @Test
    public void selectByForeignKey() throws Exception {
        int i = ho.selectByForeignKey(2, 2);
        System.out.println(i);
    }

    @Test
    public void selectById() {
        HireEntity hire = ho.selectById(4);
        System.out.println(hire.toJSON());
//        System.out.println(hire.getTime().getId());
        System.out.println(gson.toJson(hire.getClassroom()));
        System.out.println(gson.toJson(hire.getTeacher()));
    }

    @Test
    public void hireByTeacher() {
        //成功，insert操作，已注释
//        TeacherEntity teacherEntity = new TeacherOperation().selectById(2);
//        ClassroomEntity classroomEntity = new ClassroomOperation().selectById(6);
//        TimeEntity time = new TimeOperation().selectById(2);
//        System.out.println(ho.hireByTeacher(teacherEntity, classroomEntity, timeEntity));

        TimeEntity time = new TimeEntity();
        time.setDay(new Date());
        time.setTime(gson.toJson(new String[]{"7", "11"}));
        System.out.println(ho.hireByTeacher(2, 6, time));
    }

    @Test
    public void selectByClassroom() {
        List<HireEntity> hireEntities = ho.selectByClassroom(2);
        for (HireEntity hire : hireEntities) {
            System.out.print(hire.toJSON());
            System.out.println(hire.getTeacher().toJSON());
        }
    }
    @Test
    public void selectByTeacher() {
        List<HireEntity> hireEntities = ho.selectByTeacher(2);

        Collections.sort(hireEntities, new Comparator<HireEntity>() {
            @Override
            public int compare(HireEntity o1, HireEntity o2) {
                return - o1.getTime().getDay().compareTo(o2.getTime().getDay());
            }
        });

//        for (int i = 0; i < hireEntities.size() - 1; i++) {
//            if (hireEntities.get(i) == null) {
//                return;
//            }
//            TimeEntity time = hireEntities.get(i).getTime();
//            TimeEntity nextTime = hireEntities.get(i + 1).getTime();
//            if (time.getDay().equals(nextTime.getDay())) {
//                List<String> s = gson.fromJson(time.getTime(), new TypeToken<List<String>>() {}.getType());
//                List<String> s2 = gson.fromJson(nextTime.getTime(), new TypeToken<List<String>>(){}.getType());
//
//            }
//        }

        for (HireEntity hire : hireEntities) {
            System.out.println(hire.toJSON());
            System.out.println(hire.getClassroom().toJSON());
            System.out.println(hire.getTime().toJSON());
            System.out.println("======");

            ClassroomEntity classroom = hire.getClassroom();
            TimeEntity time = hire.getTime();
            String s = time.getTime();
            List<String> o = gson.fromJson(s, new TypeToken<List<String>>() {}.getType());
            System.out.println("list: " + gson.toJson(o));
            System.out.println("size: " + o.size());

            System.out.println("-----------------");
        }
    }

    @Test
    public void selectWhichBeHired() throws Exception {
        List<TimeEntity> list = ho.selectWhichBeHired(2, 6);
        Collections.sort(list, new Comparator<TimeEntity>() {
            @Override
            public int compare(TimeEntity o1, TimeEntity o2) {
                return - o1.getDay().compareTo(o2.getDay());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            TimeEntity timeEntity = list.get(i);

            System.out.println(timeEntity.toJSON());
            Date date = new Date();
            System.out.println(date.compareTo(timeEntity.getDay()));
            List<String> l = gson.fromJson(timeEntity.getTime(), new TypeToken<List<String>>(){}.getType());
            System.out.println(l.size());
        }

    }

    //测试数字，无意义
    @Test
    public void test() {
        int[] arr = {2, 5};

        int start, end;
        start = end = arr[0];
        for (int k = 0; k < arr.length; k++) {
            //最后一次循环
            if (k == arr.length - 1) {
                if (arr.length == 2) {
                    if (arr[0] + 1 != arr[1]) {
                        System.out.println(arr[0] + ":00-" + (arr[0] + 1) + ":00");
                        System.out.println(arr[1] + ":00-" + (arr[1] + 1) + ":00");
                    }
                } else {
                    //处理不了类似{2， 5}这样的数组
                    System.out.println(start + ":00-" + (arr[k] + 1) + ":00");
                }
                break;
            }

            if (k == 0) {
                continue;
            }

            if (end + 1 == arr[k]) {
                end = arr[k];
            } else {
                System.out.println(start + ":00-" + (end + 1) + ":00");
                start = end = arr[k];
            }
        }
    }
}