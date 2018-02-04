package com.ning.controller;

import com.google.gson.Gson;
import com.ning.DAO.TimeEntity;
import com.ning.DO.HireOperation;
import com.ning.factory.Constant;
import com.ning.factory.IGson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sun.reflect.annotation.AnnotationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ning on 2017/7/10.
 */
public class Time extends BaseController {
    private String time;
    private String hireTime;

    private HireOperation ho = new HireOperation();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHireTime() {
        return hireTime;
    }

    public void setHireTime(String hireTime) {
        this.hireTime = hireTime;
    }

    public String freetime() throws Exception {
        ready();
        System.out.println("time: " + time);
        System.out.println("id: " + session.getAttribute("teacher_id"));
        session.setAttribute("time", time);
        return SUCCESS;
    }

    public String hire() throws Exception {
        if (hireTime == null) {
            return ERROR;
        }

        ready();
        String[] split = hireTime.split(",");
        String json = new Gson().toJson(split);
        System.out.println("hireTime: " + json);
        int teacher_id = (int) session.getAttribute("teacher_id");
        System.out.println("teacher_id: " + teacher_id);
        int classroom_id = (int) session.getAttribute("classroom_id");
        System.out.println("classroom_id: " + classroom_id);

        TimeEntity timeEntity = new TimeEntity();
        String time = (String) session.getAttribute("time");
        timeEntity.setDay(Constant.simpleDateFormat.parse(time));
        timeEntity.setTime(json);
        boolean b = ho.hireByTeacher(teacher_id, classroom_id, timeEntity);

        if (b) {
            return SUCCESS;
        }
        return ERROR;
    }

}
