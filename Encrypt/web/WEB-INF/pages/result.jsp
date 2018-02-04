<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ning
  Date: 1/4/2018 0004
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>结果</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
</head>
<body>

<br><br><br>

<div class="row">
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">

    </div>
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="/encrypt">加密</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <a class="navbar-brand text-primary" href="/result"><p class="text-primary">结果</p></a>
                </div>
            </div>
        </nav>
    </div>
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
    </div>
</div>

<div class="container table-responsive">
    <br><br><br><br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>加密类型</th>
            <th>状态</th>
            <th>文本</th>
            <th>秘钥</th>
            <th>结果</th>
        </tr>
        </thead>
        <tbody>
        <tr class="success">
            <td>${info.id}#解析结果</td>
            <td>${info.eType.note}#${info.actionType == 0 ? "加密" : "解密"}</td>
            <td>${info.success ? "成功" : "失败"}</td>
            <td>${info.text}</td>
            <td>${info.key}</td>
            <td>${info.actionType == 0 ? info.ciphertext : info.plaintext}</td>
        </tr>

        <tr class="active">
            <td>#</td>
            <td>#</td>
            <td>#</td>
            <td>#</td>
            <td>#</td>
            <td>#</td>
        </tr>

        <c:forEach items="${infoList}" begin="0" end="${infoList.size()}" step="1" var="item">
            <tr class="info">
                <td>${item.id}</td>
                <td>${item.eType.note}#${item.actionType == 0 ? "加密" : "解密"}</td>
                <td>${item.success ? "成功" : "失败"}</td>
                <td>${item.text}</td>
                <td>${item.key}</td>
                <td>${item.actionType == 0 ? item.ciphertext : item.plaintext}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
