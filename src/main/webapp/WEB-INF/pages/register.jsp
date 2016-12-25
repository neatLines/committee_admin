<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <meta charset="utf-8">
    <title>注册 - 居委会管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/register-login1.css">
</head>
<%--<script type="text/javascript">--%>
    <%--function jiami(){--%>
        <%--var user0 = user.value;--%>
        <%--var user1 = hex_sha1(user0);--%>
        <%--var mima0 = password.value;--%>
        <%--var mima1 = hex_sha1(mima0);--%>
        <%--var name0 = name.value;--%>
        <%--var name00 = hex_sha1(name0);--%>
        <%--var age0 = age.value;--%>
        <%--var age00 = hex_sha1(age0);--%>
        <%--var sex0 = sex.value;--%>
        <%--var sex00 = hex_sha1(sex0);--%>
        <%--var link0 = link.value;--%>
        <%--var link00 = hex_sha1(link0);--%>
        <%--var sex0 = sex.value;--%>
        <%--var sex00 = hex_sha1(sex0);--%>

        <%--if(user0==""||mima0==""||name0==""||age0==""||sex0==""||link0=="")--%>
        <%--{alert("请填写完整信息！！！");--%>
            <%--return false;--%>
        <%--}--%>
        <%--return true;--%>
    <%--}--%>
<%--</script>--%>
<body>
<div id="box"></div>
<div class="cent-box register-box">
    <div class="cent-box-header">
        <h1 class="main-title">居委会</h1>
        <h2 class="sub-title">全心全意为人民服务</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="login.html">登录</a>
                <a href="register.html" class="active">注册</a>

            </div>
        </div>

        <div class="login form">
            <div class="group">

                <div class="group-ipt user">
                    <input type="text" name="user" id="user" class="ipt" placeholder="输入您喜欢的用户名" required>
                </div>
                <div class="group-ipt password">
                    <input type="password" name="password" id="password" class="ipt" placeholder="设置登录密码" required>
                </div>
                <div class="group-ipt user">
                    <input type="text" name="name" id="name" class="ipt" placeholder="姓名" required>
                </div>
                <div class="group-ipt user">
                    <input type="text" name="sex" id="sex" class="ipt" placeholder="性别" required>
                </div>
                <div class="group-ipt user">
                    <input type="text" name="age" id="age" class="ipt" placeholder="年龄" required>
                </div>
                <div class="group-ipt user">
                    <input type="text" name="link" id="link" class="ipt" placeholder="联系方式" required>
                </div>

            </div>
        </div>

        <div class="button">
            <button class="login-btn register-btn" id="button">注册</button>
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
            var mima0 = password.value;
            var mima1 = hex_sha1(mima0);
            $.ajax({
                type: "POST",
                url: "/registerp",
                data:JSON.stringify( {userName:$("#user").val(),password:mima1,uName:$("#name").val(),uAge:$("#age").val(),uSex:$("#sex").val(),phoneNumber:$("#link").val()}),
                success: function(result){
                    alert(result.info);
                    if (result.info=="success") {//判断。。。
                        location.href = "login";
                    }
                },
                contentType: "application/json",
                dataType: "json"
            });
        });
    });
</script>