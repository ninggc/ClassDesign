<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@page import="com.ning.controller.Login"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>沈阳理工大学教室管理系统——请登录</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

    <% if(session.getAttribute("name") != null) {
        System.out.println("login:name:" + session.getAttribute("name"));
    %>
    <script  type="text/javascript">
        window.self.location = "index.jsp"
    </script >
    <% } %>

</head>
<body style="background-color: #E2E2E2;">
<!-- 
<script type="text/javascript">
    window.onload = function(){
        var userNameValue = "cook";//getCookieValue("userName");  
        document.getElementById("name").value = userNameValue;
        var userPassValue = getCookieValue("userPass");
        document.getElementById("password").value = userPassValue;
    }
</script>
 -->

<%
    System.out.println("error" + session.getAttribute("error"));
%>

<div class="container">
    <div class="row text-center " style="padding-top:100px;">
        <div class="col-md-12">
            <%--<img src="assets/img/logo-book.png" />--%>
        </div>
    </div>
    <div class="row ">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

            <div class="panel-body">
                <form role="form" action="login">
                <br>
                    <h5>
                        <%
                            if (session.getAttribute("error") != null) {
                                out.print(session.getAttribute("error"));
                            }
                        %>
                    </h5>
                    <!--
                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input id="remenberPassword" type="checkbox" /> 记住密码
                        </label>
                        <span class="pull-right">
                                                   <a href="index.jsp" >忘记密码? </a>
                                            </span>
                    </div>
                    -->

                    <form action="login">
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                            <input id="name" type="text" class="form-control" placeholder="用户名 " name="name" value="ning"/>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <input id="password" type="password" class="form-control"  placeholder="密码"  name="password" value="123"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary " value="登录" />
                        </div>

                    </form>
                    <br>
                    上帝?   <a href="index.jsp" >点击此处</a>转到<a href="index.jsp">首页</a>
                </form>
            </div>

        </div>


    </div>
</div>

</body>
</html>
