package com.ning.controller;

import com.google.gson.Gson;
import com.ning.DAO.TeacherEntity;
import com.ning.DO.TeacherOperation;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ning on 2017/7/12.
 */
public class TeacherManage extends BaseController {
    private TeacherOperation to = new TeacherOperation();
    private TeacherEntity teacher = new TeacherEntity();

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }


    public String update() throws IOException {
        ready();
        int id = (int) session.getAttribute("teacher_id");

        TeacherEntity te = to.selectById(id);
        te.setName(teacher.getName());
        te.setCourse(teacher.getCourse());
        te.setCareerTitle(teacher.getCareerTitle());

        boolean flag = to.update(te);

        System.out.println(flag);

        if (teacher != null) {
            System.out.println(new Gson().toJson(teacher));
        } else {
            System.out.println("null");
        }

        return SUCCESS;
    }
}
