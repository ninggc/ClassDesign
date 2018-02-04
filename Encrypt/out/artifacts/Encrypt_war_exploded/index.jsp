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
    <title>$Title$</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          type="text/css"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
</head>

<body>
<div>
    <br><br><br><br><br>
</div>

<div class="row">
    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">

    </div>
    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="/encrypt">
                <img src="assets/img/encrypt.png" class="img-rounded">
                <h1>加密</h1>
            </a>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="/result">
                <img src="assets/img/result.png" class="img-rounded" alt="Image">
                <h1>结果</h1>
            </a>
        </div>
    </div>
    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">

    </div>
</div>


<script type="text/javascript">
    function item(value) {
        alert('item');
    }
</script>
</body>
</html>
