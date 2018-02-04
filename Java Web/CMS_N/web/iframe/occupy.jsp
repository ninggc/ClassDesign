<%@ page import="com.ning.DAO.ClassroomEntity" %>
<%@ page import="com.ning.DO.HireOperation" %>
<%@ page import="com.ning.DAO.TeacherEntity" %>
<%@ page import="com.ning.DO.TeacherOperation" %>
<%@ page import="com.ning.DO.ClassroomOperation" %>
<%@ page import="com.ning.DAO.HireEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %><%--
  Created by IntelliJ IDEA.
  User: Ning
  Date: 12/19/2017 0019
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>租借情况</title>
</head>
<body>
<%
    Gson gson = new Gson();
    HireOperation ho = new HireOperation();
    int teacher_id = (int) session.getAttribute("teacher_id");
    List<HireEntity> hireEntities = ho.selectByTeacher(teacher_id);

    Collections.sort(hireEntities, new Comparator<HireEntity>() {
        @Override
        public int compare(HireEntity o1, HireEntity o2) {
            return - o1.getTime().getDay().compareTo(o2.getTime().getDay());
        }
    });

//    System.out.println(gson.toJson(hireEntities));
    for (int i = 0; i < hireEntities.size(); i++){
        System.out.println(hireEntities.get(i).getTime().getTime());
        String name = hireEntities.get(i).getTeacher().getName();
        if(i == 0) {
            out.print("<h1>" + name + "租用教室情况</h1>");
        }
        String number = hireEntities.get(i).getClassroom().getNumber();
        Date date = hireEntities.get(i).getTime().getDay();

        Date day = hireEntities.get(i).getTime().getDay();
        if (i != 0) {
            Date dayBefore = hireEntities.get(i - 1).getTime().getDay();
            if (!day.equals(dayBefore)) {
                out.print("=========================");
                out.print("<h3>时间为：" + date + "</h3>");
            } else {
                out.println("</br>");
            }
        } else {
            out.print("=========================");
            out.print("</br><h3>时间为：" + date + "</h3>");
        }
%>

<h3>教室：<%=number%> </br></h3>

<div class="row">
    <div>
        <h3>已分配时间：
            <%
                String s = hireEntities.get(i).getTime().getTime();
                List<String> split = gson.fromJson(s,new TypeToken<List<String>>(){}.getType());
                int[] arr = new int[split.size()];
                for (int j=0;j<arr.length;j++){
                    String s1 = split.get(j);
                    if ("".equals(s1)) {
                        continue;
                    }
                    arr[j] = Integer.parseInt(s1);
                }

                int start, end;
                start = end = arr[0];
                for (int k = 0; k < arr.length; k++) {
                    //最后一次循环
                    if (k == arr.length - 1) {
                        if (arr.length == 2) {
                            if (arr[0] + 1 != arr[1]) {
                                out.print(arr[0] + ":00-" + (arr[0] + 1) + ":00");
                                out.println("&nbsp;" + arr[1] + ":00-" + (arr[1] + 1) + ":00");
                            }
                        } else {
                            //处理不了类似{2， 5}这样的数组
                            out.println(start + ":00-" + (arr[k] + 1) + ":00");
                        }
                        break;
                    }

                    if (k == 0) {
                        continue;
                    }

                    if (end + 1 == arr[k]) {
                        end = arr[k];
                    } else {
                        out.println(start + ":00-" + (end + 1) + ":00");
                        start = end = arr[k];
                    }
                }

//                int start, end;
//                start = end = num[0];
//                for (int k = 0; k < num.length; k++){
//                    start = num[k];
//                    if (k == num.length - 1) {
//                        out.print("&nbsp;&nbsp;&nbsp;" + start +":00-"+ (num[k] + 1) + ":00");
//                        break;
//                    }
//
//                    if (end + 1 == num[k]) {
//                        end = num[k];
//                        continue;
//                    } else {
//                        out.print("&nbsp;&nbsp;&nbsp;" + start +":00-"+ (end + 1) + ":00");
//                        start = end = num[k];
//                    }
////                    out.print("&nbsp;&nbsp;&nbsp;" + start + ":00-" + (start + 1) + ":00");
//                }
            %>
        </h3>
    </div>
</div>
<%
    }
    out.print("=========================");
%>
</body>
</html>
