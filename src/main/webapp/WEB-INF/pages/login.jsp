<%--
  Created by IntelliJ IDEA.
  User: fuyipeng
  Date: 2016/11/9
  Time: 下午6:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <meta charset="utf-8">
    <title>登录 - 居委会管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/register-login1.css">
</head>
<%--<script type="text/javascript">--%>
    <%--function jiami(){--%>
        <%--var user0 = user.value;--%>
        <%--var user1 = hex_sha1(user0);--%>
        <%--var mima0 = password.value;--%>
        <%--var mima1 = hex_sha1(mima0);--%>

        <%--if(user0==""||mima0=="")--%>
        <%--{alert("请填写完整信息！！！");--%>
            <%--return false;--%>
        <%--}--%>
        <%--return true;--%>
    <%--}--%>
<%--</script>--%>
<body>
<div id="box"></div>
<div class="cent-box">
    <div class="cent-box-header">
        <h1 class="main-title">居委会</h1>
        <h2 class="sub-title">全心全意为人民服务</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="login.html" class="active">登录</a>
                <a href="register.html">注册</a>
                <div class="slide-bar"></div>
            </div>
        </div>

        <div class="login form">
            <div class="group">
                <div class="group-ipt email">
                    <input type="text" name="user" id="user" class="ipt" placeholder="输入您的账号" required>
                </div>
                <div class="group-ipt password">
                    <input type="password" name="password" id="password" class="ipt" placeholder="输入您的登录密码" required>
                </div>
            </div>
        </div>

        <div class="button">
            <button class="login-btn register-btn" id="button">登录</button>
        </div>
    </div>
</div>

<div class="footer">
    <p>居委会</p>

</div>
<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/sha1.js' type="text/ecmascript" ></script>


</body>
</html>

<script>
    $(document).ready(function(){
            $("#button").click(function(){
            $.ajax({
            type: "POST",
            url: "/loginp",
            data:JSON.stringify( {userName:$("#user").val(),password:$("#password").val()}),
            success: function(result){
                alert(result.info);
                if (result.info=="user") {//判断。。。
                    location.href = "selfmanage";
                } else if (result.info=="admin") {
                    location.href = "selfmanage";
                } else if (result.info=="superAdmin"){
                    location.href = "selfmanage";
                }
            },
            contentType: "application/json",
            dataType: "json"
            });
            });
    });
</script>