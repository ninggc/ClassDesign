<%@ page import="com.ning.DO.ClassroomOperation" %>
<%@ page import="com.ning.DAO.ClassroomEntity" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="../table/style.css" />

    <title>书籍检索</title>

    <style type="text/css">
        <!--
        @import url("style.css");
        -->
    </style>


</head>

<body>

<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    <tr>
        <th scope="col" class="rounded-start">#</th>
        <th scope="col" class="rounded-q1">id</th>
        <th scope="col" class="rounded-q1">编号</th>
        <th scope="col" class="rounded-q1">人数</th>
        <th scope="col" class="rounded-q1">附加信息</th>
    </tr>
    </thead>

    <tbody>
    <%
        ClassroomOperation co = new ClassroomOperation();
        List<ClassroomEntity> list = co.selectAll();
        int count = 0;
        for (ClassroomEntity c : list) {
    %>

    <tr id=<%=count%>>
        <td>
            <%
                out.print(++count);
            %>
        </td>
        <td id="id">
            <%
                out.print(c.getId());
            %>
        </td>
        <td>
            <%
                out.print(c.getNumber());
            %>
        </td>
        <td>
            <%
                out.print(c.getCapacity());
            %>
        </td>
        <td>
            <%
                if (c.getExtra().length() < 10) {
                    out.print(c.getExtra());
                } else {
                    out.print(c.getExtra().substring(0, 9) + "...");
                }
            %>
        </td>
    </tr>

    <%
        }
    %>

    </tbody>


    <tfoot>
    <tr>
        <td colspan="4" class="rounded-foot-left"><em> 查询成功, 共有<%
            out.print(count);
        %>条数据.
        </em></td>
        <td class="rounded-foot-right">&nbsp;</td>
    </tr>
    </tfoot>
</table>

<script type="text/javascript">
    var ths = document.getElementsByTagName('th'),
        trs = document.getElementsByTagName('tr'),
        tds = document.getElementsByTagName('td');

    var td_ids = [];

    //将id为'id'的innerHTML信息放入数组td_ids中
    for (var k = 0; k < tds.length; k++) {
        if (tds[k].id == 'id') {
            td_ids.push(tds[k].innerHTML);
//            alert(tds[k].innerHTML);
        }

    }

//    //对表格头的点击响应事件
//    for (var i = 0; i < ths.length; i++) {
//        ths[i].index = i;
//        ths[i].onclick = function() {
//            alert(this.index);
//            tds[this.index].style.backgroundColor = 'red';
//        }
//    }

    //对表格行的点击响应事件
    for (var j = 0; j < trs.length; j++) {
        trs[j].onclick = function() {
//            alert(this.id)
            id  = td_ids[parseInt(this.id)];
//            alert(id);
            window.self.location = "showCInfo!showInfo.action?id=" + id;
        }
    }
</script>
</body>
</html>