<%@ page import="com.ning.DO.ClassroomOperation" %>
<%@ page import="com.ning.DAO.ClassroomEntity" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.ning.factory.Constant" %><%--
  Created by IntelliJ IDEA.
  User: ning
  Date: 2017/7/10
  Time: 13:37
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
        ClassroomOperation co = new ClassroomOperation();
        ClassroomEntity c = co.selectById(Integer.valueOf(session.getAttribute("classroom_id").toString()));
        System.out.println(session.getAttribute("classroom_id"));
    %>

    <div class="container">
        <div class="jumbotron">
            <div class="row"><h2>教室信息</h2></div>
            <div class="col-md-6">
                <h4>编号: <%=c.getNumber()%></h4>
            </div>
            <div class="col-md-6">
                <h4>人数: <%=c.getCapacity()%></h4>
            </div>
            <div class="row">
                <h4>附加信息: <%=c.getExtra()%></h4>
            </div>
        </div>
        <div class="jumbotron">
            <div class="row">空闲时间</div>
            <%
                Calendar calendar = Calendar.getInstance();
                for (int i = 0; i < 7; i++) {
                    calendar.add(Calendar.DATE, i == 0 ? 0 : 1);
            %>

            <a href="#" onclick="time_click(this.innerHTML)">
                <%
                        out.print(Constant.simpleDateFormat.format(calendar.getTime()) + "\t");
                %>
            </a>
            &nbsp;&nbsp;&nbsp;
            <% } %>

        </div>
    </div>

    <script>
        function time_click(text) {
            window.self.location="freetime!freetime.action?time=" + text;
        }
    </script>
</body>
</html>
