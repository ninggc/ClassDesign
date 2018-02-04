<%@ page import="java.util.Date" %>
<%@ page import="com.ning.factory.Constant" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: ning
  Date: 2017/7/10
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Calendar c = Calendar.getInstance();
        for (int i = 1; i < 8; i++) {
            c.add(Calendar.DATE, 1);
            out.print(Constant.simpleDateFormat.format(c.getTime()) + "\teacher");
        }
//        out.print(Constant.simpleDateFormat.format(c));
    %>

</body>
</html>
