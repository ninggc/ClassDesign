package com.ning.controller;

import com.google.gson.Gson;
import com.ning.DAO.TeacherEntity;
import com.ning.DO.ClassroomOperation;
import com.ning.DO.TeacherOperation;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ning on 2017/7/4.
 */
public class Login extends BaseController {

    ClassroomOperation co = new ClassroomOperation();
    TeacherOperation to = new TeacherOperation();


    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        return "Hello world";
    }

    public void test1() {
        try {
            String json = ready();
            System.out.println(json);
            String result = new Gson().toJson(co.selectById(1));
            response.getWriter().append(result);
            System.out.println(result);
            System.out.println("t");
            System.out.println("exit0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Gson gson = new Gson();

    private TeacherEntity teacher;

    public String login() throws IOException {
        ready();
        teacher = to.verify(name, password);
        if (teacher != null) {
            System.out.println(new Gson().toJson(teacher));
            if ("SUCCESS".equals(teacher.getPassword())) {
                //保存teacher的id和name
                session.setAttribute("teacher_id", teacher.getId());
                session.setAttribute("name", teacher.getName());
                System.out.println(session.getAttribute("name"));
                return SUCCESS;
            } else if("FAILED".equals(teacher.getPassword())) {
                session.setAttribute("error", "用户名或密码错误！");
            }
        } else {
            session.setAttribute("error", "用户不存在！");
        }

        return ERROR;
    }

    public String exit() throws IOException {
        ready();

        System.out.println("exit:name" + session.getAttribute("name"));
        System.out.println("exit:teacher_id" + session.getAttribute("teacher_id"));

        session.removeAttribute("name");
        session.removeAttribute("teacher_id");

        return SUCCESS;
    }

    public String selectById() throws IOException {
        ready();

        return ERROR;
    }
}
