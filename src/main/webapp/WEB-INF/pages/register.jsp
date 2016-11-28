<%--
  Created by IntelliJ IDEA.
  User: fuyipeng
  Date: 2016/11/13
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>注册</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<%--private String uName;--%>
<%--private int uAge;--%>
<%--private byte uSex;--%>
<%--private String phoneNumber;--%>
<%--private String userName;--%>
<%--private String password;--%>
<body>
<div class="container">
    <h1>添加用户</h1>
    <hr/>
    <form:form action="registerp" method="post" commandName="user" role="form">
        <div class="form-group">
            <label for="uName">名字:</label>
            <input type="text" class="form-control" id="uName" name="uName" placeholder="Enter Name:"/>
        </div>
        <div class="form-group">
            <label for="uAge">年龄:</label>
            <input type="number" class="form-control" id="uAge" name="uAge" placeholder="Enter Age:"/>
        </div>
        <div class="form-group">
            <select class="form-control" id="uSex" name="uSex">
                <option>男</option>
                <option>女</option>
                <option>空</option>
            </select>
        </div>
        <div class="form-group">
            <label for="phoneNumber">手机号码:</label>
            <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Enter phoneNumber:"/>
        </div>
        <div class="form-group">
            <label for="userName">用户名:</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter userName:"/>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter password:"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>