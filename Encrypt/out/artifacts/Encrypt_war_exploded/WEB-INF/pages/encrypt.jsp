<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ning
  Date: 1/3/2018 0003
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>加密</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <script
            src="http://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>

<body>

<script type="text/javascript">
    $(document).ready(function (value) {
        var text = "${text}";
        if (text.trim() === "") {
            console.log("null");
        } else {
            console.log("not null");
            $("#text").attr("value", text);
        }
    })
</script>

<br><br><br>

<div class="row">
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">

    </div>
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="/encrypt"><p class="text-primary">加密</p></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <a class="navbar-brand" href="/result">结果</a>
                </div>
            </div>
        </nav>
    </div>
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
    </div>
</div>
<div>
    <br><br><br>
</div>

<div class="row">
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"></div>

    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
        <form class="form-horizontal" action="/encrypt" method="post">
            <div class="form-group">
                <label class="col-md-3 control-label">加密方式</label>
                <div class="col-md-9">
                    <select class="form-control" name="type" onclick="selectItem(this)">
                        <option value="0" selected>请选择</option>
                        <c:forEach items="${types}" step="1" var="item">
                            <option value="${item.order}" >${item.note}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">加密|解密</label>
                <div class="col-md-9">
                    <select class="form-control" name="actionType" onclick="selectEncryptType(this)">
                        <option value="0" >加密</option>
                        <option value="1" >解密</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="text" id="text_label" class="col-md-3 control-label">明文</label>
                <div class="col-md-9">
                    <input name="text" class="form-control" id="text" placeholder="明文">
                </div>
            </div>

            <div class="form-group" id="tip_group" style="display: none">
                <label for="tip${item.order}" class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;秘钥格式
                </label>
                <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                    <c:forEach items="${types}" step="1" var="item">
                        <div id="tip${item.order}" class="alert alert-info" style="display: none">${item.keyTip}</div>
                    </c:forEach>
                </div>
            </div>

            <div class="form-group" id="key_group">
                <label for="key" id="key_label" class="col-md-3 control-label">秘钥</label>
                <div class="col-md-9">
                    <input name="key" class="form-control" id="key" placeholder="秘钥">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">

                </div>
                <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                    <button type="submit" id="submit" class="btn btn-primary">加密</button>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">

                </div>
            </div>
        </form>
    </div>

    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"></div>
</div>

<script type="text/javascript">
    function selectItem(select) {
        var index = select.selectedIndex;
        console.log(index);

        <c:forEach items="${types}" var="item">
            var $tip = $("#tip" + ${item.order});
            if (index === ${item.order}) {
                $("#tip_group").css("display", "block");
                $tip.css("display", "block");
            } else {
                console.log(index === ${item.order});
                console.log("order: " + ${item.order});
                $tip.css("display", "none");
            }
        </c:forEach>
    }

    function selectEncryptType(select) {
        var index = select.selectedIndex;
        var $submit = $("#submit");
        var $text = $("#text");
        var $plaintext_label = $("#plaintext_label");
        if (index === 1) {
            $submit.text("解密");
            $plaintext_label.text("密文");
            $text.attr("placeholder", "密文");
        } else {
            $submit.text("加密");
            $plaintext_label.text("明文");
            $text.attr("placeholder", "明文");
        }
    }
</script>
</body>
</html>