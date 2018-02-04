<%@ page import="com.ning.DO.HireOperation" %>
<%@ page import="com.ning.DAO.TimeEntity" %>
<%@ page import="com.ning.factory.Constant" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: ning
  Date: 2017/7/10
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
</head>
<body>
    <%
        String time = session.getAttribute("time").toString();

        HireOperation ho = new HireOperation();
        int teacher_id = (int) session.getAttribute("teacher_id");
        int classroom_id = (int) session.getAttribute("classroom_id");

        Gson gson = new Gson();
        List<String> busyList = new ArrayList<>();
        List<TimeEntity> timeEntities = ho.selectWhichBeHired(teacher_id, classroom_id);

        SimpleDateFormat simpleDateFormat = Constant.simpleDateFormat;
        for (int i = 0; i < timeEntities.size(); i++) {
            Date day = timeEntities.get(i).getDay();

            if (simpleDateFormat.format(day).equals(time.trim())) {
                List<String> e = gson.fromJson(timeEntities.get(i).getTime(), new TypeToken<List<String>>() {}.getType());
                busyList.addAll(e);
            }
        }

        busyList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(Integer.valueOf(o1), Integer.valueOf(o2));
            }
        });
        int position = 0;
        String checkboxDiasble = "disabled=\"disabled\"";
        String busyTip = "（已被占用）";
        boolean flag = false;

//        out.println(gson.toJson(busyList));

        ArrayList<Integer> hour = new ArrayList<Integer>();
        for (int i = 6; i <= 20; i++) {
            hour.add(i);
        }
        int j = 0;
    %>

    <div class="container">

        <%--<div class="page-header">--%>
            <%--<ul class="nav nav-pills">--%>
                <%--<li role="presentation" class="active">已分配</li>--%>
                <%--<li role="presentation">申请</li>--%>
            <%--</ul>--%>
        <%--</div>--%>

        <div class="row">
            <h2>
                教室已分配情况&nbsp;&nbsp;&nbsp;时间：<%=time%>
            </h2>
        </div>

        <div class="row">
            <div class="col-md-4">
                <%
                    for (int i = 0; i < 5; i++) {
                %>
                <div class="checkbox">
                    <h4>
                        <label>
                            <% int h = hour.get(j++); %>
                            <input type="checkbox" id="<%out.print(h);%>" <%if (position < busyList.size()) if (String.valueOf(h).equals(busyList.get(position))) {++position;flag=true;out.print(checkboxDiasble);}%>>
                            <%
                                out.print(h + ":00-" + (h + 1) + ":00");
                                if (flag) {
                                    out.print(busyTip);
                                }
                            %>
                        </label>
                    </h4>
                    <br>
                </div>
                <%
                        flag = false;
                    }
                %>
            </div>
            <div class="col-md-4">
                <%
                    for (int i = 5; i < 10; i++) {
                %>
                <div class="checkbox">
                    <h4>
                        <label>
                            <% int h = hour.get(j++); %>
                            <input type="checkbox" id="<%out.print(h);%>"
<%
//如果这个时间段已被占用，就设置不可选中
if (position < busyList.size())
    if (String.valueOf(h).equals(busyList.get(position))) {
    ++position;
    flag = true;
    out.print(checkboxDiasble);
}
%>>
                            <%
                                out.print(h + ":00-" + (h + 1) + ":00");
                                if (flag) {
                                    out.print(busyTip);
                                }
                            %>
                        </label>
                    </h4>
                    <br>
                </div>
                <%
                        flag = false;
                    }
                %>

            </div>
            <div class="col-md-4">
                <%
                    for (int i = 10; i < 14; i++) {
                %>
                <div class="checkbox">
                    <h4>
                        <label>
                            <% int h = hour.get(j++); %>
                            <input type="checkbox" id="<%out.print(h);%>" <%if (position < busyList.size()) if (String.valueOf(h).equals(busyList.get(position))) {++position;flag=true;out.print(checkboxDiasble);}%>>
                            <%
                                out.print(h + ":00-" + (h + 1) + ":00");
                                if (flag) {
                                    out.print(busyTip);
                                }
                            %>
                        </label>
                    </h4>
                    <br>

                </div>
                <%
                        flag = false;
                    }
                %>

            </div>
        </div>

        <div class="row">
            <input class="btn btn-primary" type="button" value="提交" onclick="submit()">
        </div>
    </div>

<script type="text/javascript">
    function submit() {

        var arrayobj = new Array();
        for (var x=6;x<20;x++){
            var v = document.getElementById(x);
            if(v.checked){
                arrayobj.push(x);
            }
        }
        var time=arrayobj.join(",");
        window.self.location="hire!hire.action?hireTime=" + time;
    }
</script>
</body>
</html>
