package com.ning.controller;

import com.ning.DAO.TeacherEntity;
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
 * Created by ning on 2017/7/11.
 */
public class ClassroomManage extends BaseController {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private TeacherEntity teacher;

    public String showInfo() throws Exception {
        ready();
        session.setAttribute("classroom_id", id);
        return SUCCESS;
    }
}
