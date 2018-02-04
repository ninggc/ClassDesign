<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>欢迎使用——沈阳理工大学教室管理系统</title>
    
    
    <link rel="stylesheet"  type="text/css"  href="assets/css/table.css"/>
    

    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
       <!--CUSTOM BASIC STYLES-->
    <link href="assets/css/basic.css" rel="stylesheet" />
    <!--CUSTOM MAIN STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>

	<!-- 窗口关闭时执行代码 -->
	<%--<script>--%>
	<%--window.onbeforeunload = function(event) { --%>
		<%--url = "logout!logout.action";--%>
		<%--alert(url);--%>
		<%--window.location.href = url;--%>
	<%--}--%>
	<%--</script>--%>

    <%
        System.out.println("index:teacher_id:" + session.getAttribute("teacher_id"));
        System.out.println("index:name:" + session.getAttribute("name"));
    %>
	
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" onclick="changeJSP(this, 'userInfo')" href="#">
                	<% if(session.getAttribute("name") == null) {
                		out.print("匿名登录");
                	} else{
                		out.print("你好  " + session.getAttribute("name"));
                	}
                	%>
                </a>
            </div>

            <div class="header-right">
	                <%--<a href="#" class="btn btn-info" title="New Message"><b>30 </b><i class="fa fa-envelope-o fa-2x"></i></a>--%>
	                <%--<a href="#" class="btn btn-primary" title="New Task"><b>40 </b><i class="fa fa-bars fa-2x"></i></a>--%>
	                
	                <a class="btn btn-danger" title="Logout" onclick="exit()"> <b>安全退出 </b>
                        <%--<i class="fa fa-exclamation-circle fa-2x"></i>--%>
                    </a>

            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <div class="user-img-div">
                            <div class="inner-text">
                                <a  onclick="changeJSP(this, 'userInfo')"><b>
                                    <% if(session.getAttribute("name") == null) {
                                        out.print("匿名登录");
                                    } else{
                                        out.print("你好  " + session.getAttribute("name"));
                                    }
                                    %>
                                </b>
                                </a>
                            </div>
                            <!-- class="img-thumbnail"> -->

                        	<img src="管理员.png"  onclick="changeJSP(this, 'userInfo')" >
                        </div>

                    </li>
                    

                    <li>
                        <a class="active-menu" href="index.jsp"><i class="fa fa-dashboard "></i>首页</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-send "></i>教室信息<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                            <li>
                                <a href="#" id="showALLC" onclick="changeJSP(this, 'showAllC')"><i class="fa fa-toggle-on"></i>查看所有教室</a>
                            </li>
                            <li>
                                <a href="#" id="showOccupy" onclick="changeJSP(this, 'showOccupy')"><i class="fa fa-bell"></i>查询租借情况</a>
                            </li>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'updateReader')"><i class="fa fa-circle-o "></i>更改信息</a>--%>
                            <%--</li>--%>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'addReader')"><i class="fa fa-code "></i>添加信息</a>--%>
                            <%--</li>--%>
                           <%----%>
                        </ul>
                    </li>
                     <%--<li>--%>
                        <%--<a href="#"><i class="fa fa-yelp "></i>图书信息<span class="fa arrow"></span></a>--%>
                         <%--<ul class="nav nav-second-level">--%>
							<%--<li>--%>
                                <%--<a href="#" id="showBook" onclick="changeJSP(this, 'showBook')"><i class="fa fa-toggle-on"></i>查看所有信息</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'searchBook')"><i class="fa fa-bell "></i>查询信息</a>--%>
                            <%--</li>--%>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'updateBook')"><i class="fa fa-circle-o "></i>更改信息</a>--%>
                            <%--</li>                            --%>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'addBook')"><i class="fa fa-code "></i>添加信息</a>--%>
                            <%--</li>--%>
                           <%----%>
                        <%--</ul>--%>
                    <%--</li>--%>
                     <%--<li>--%>
                        <%--<a href="#"><i class="fa fa-coffee "></i>书店信息<span class="fa arrow"></span></a>--%>
                         <%--<ul class="nav nav-second-level">--%>
							<%--<li>--%>
                                <%--<a href="#" id="showStore" onclick="changeJSP(this, 'showStore')"><i class="fa fa-toggle-on"></i>查看所有信息</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'searchStore')"><i class="fa fa-bell "></i>查询信息</a>--%>
                            <%--</li>--%>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'updateStore')"><i class="fa fa-circle-o "></i>更改信息</a>--%>
                            <%--</li>--%>
                             <%--<li>--%>
                                <%--<a href="#" onclick="changeJSP(this, 'addStore')"><i class="fa fa-code "></i>添加信息</a>--%>
                            <%--</li>--%>
                           <%----%>
                        <%--</ul>--%>
                    <%--</li>  --%>
                    <%--<li>--%>
                        <%--<a href="#" onclick="changeJSP(this, 'showAPPInfo')"><i class="fa fa-sitemap "></i>APP信息</a>--%>
                    <%--</li>--%>
              
                </ul>

            </div>

        </nav>
        
        	
        	
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div id="need" class="col-md-12">


						
						<iframe name="xxx" id="frame_jsp" width="100%" height="1200" onchange="on_iframe()" onload="on_iframe()"
							frameborder="0" scrolling="YES" src="" >
    					</iframe>
    					
    					
						
                    </div>
                </div>
                <!-- /. ROW  -->
                



                <!--/.ROW-->

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->


	<!--
    	作者：906978985@qq.com
    	时间：2017-03-17
    	描述：底部提示
    -->
    <div id="footer-sec">
        &copy; Welcome | 欢迎使用
    </div>

    <script >
        function exit() {
            window.self.location = "exit!exit.action";
        }

        function on_iframe() {
//            alert('iframe');
            <%--<%--%>
                <%--System.out.println("teacher_id" + session.getAttribute("teacher_id"));--%>
                <%--if (session.getAttribute("teacher_id") == null || "".equals(session.getAttribute("teacher_id"))) {--%>
                    <%--out.print("alert('请登录');");--%>
                    <%--out.print("exit();");--%>
                <%--}--%>
            <%--%>--%>
        }
    </script>


    <!-- /. FOOTER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-3.2.1.min.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
    
    <!-- 用于更改JSP界面  -->
    <script src="assets/js/changeJSP.js"></script>
    
    <!-- 用于响应action -->
    <script src="operation-jsp/turnAction.js"></script>
</body>
</html>
