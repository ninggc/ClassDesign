<%@ page import="com.ning.DO.TeacherOperation" %>
<%@ page import="com.ning.DAO.TeacherEntity" %><%--
  Created by IntelliJ IDEA.
  User: ning
  Date: 2017/7/11
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
</head>
<body>
    <%
        TeacherOperation to = new TeacherOperation();
        int id = (int) session.getAttribute("teacher_id");
        System.out.println(id);
        TeacherEntity teacher = to.selectById(id);
    %>

    <div class="container">
        <div class="jumbotron col-md-8">
            <form class="form-group" action="updateTeacher">
                <div class="row">
                    <h2>教师信息</h2>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p>姓名 :</p>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" value="<%=teacher.getName()%>" name="teacher.name">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p>课程 :</p>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" value="<%=teacher.getCourse()%>" name="teacher.course">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p>职称 :</p>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" value="<%=teacher.getCareerTitle()%>" name="teacher.careerTitle">
                    </div>
                </div>
                <div class="row">
                    <button class="btn btn-lg btn-success" type="submit">修改</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
